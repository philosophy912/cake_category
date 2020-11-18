<template>
  <div class="basic">
    <ProductTable :data="middle" :envData="envData" @handleSizeChange="handleSizeChange" @handleCurrentChange="handleCurrentChange" @del="del" @add="add" @modify="modify" @search="search"></ProductTable>
    <ProductDialog :dialog="dialog" @closeDialog="closeDialog" :materialOptions="materialOptions" :productOptions="basicOptions" @add="addNewRow">
    </ProductDialog>
  </div>
</template>
<script>
import { queryMiddles, queryMiddleByName, delMiddle } from '@/api/middles';
import { queryBasicName } from '@/api/basics';
import { queryMaterialName } from '@/api/materials';
import ProductTable from '@/components/product/table.vue';
import ProductForm from '@/components/product/form.vue';
import ProductDialog from '@/components/product/dialog.vue';
import Logger from 'chivy';

const log = new Logger('views/Middle');
export default {
  name: 'Middle',
  mounted() {
    this.getData();
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      log.debug('beforeRouteEnter to path is ' + to.path);
      vm.getData();
    });
  },
  components: {
    ProductTable,
    ProductForm,
    ProductDialog
  },
  data() {
    return {
      middle: [],
      basicOptions: [],
      materialOptions: [],
      dialog: {
        type: this.$tools.MiddleName,
        title: '',
        show: false,
        row: '',
        left: '取消',
        right: ''
      },
      row: this.$tools.createBothRow(true),
      envData: {
        pageNo: 1,
        pageSize: 10,
        totalRows: 0,
        totalPages: 0
      }
    };
  },
  watch: {
    middle(newVal, oldVal) {
      if (newVal.length == 0) {
        this.add();
      }
    }
  },
  methods: {
    handleSizeChange(val) {
      this.envData.pageSize = val;
      this.getData();
    },
    handleCurrentChange(val) {
      this.envData.pageNo = val;
      this.getData();
    },
    getData() {
      const data = {
        envData: this.envData
      };
      queryMaterialName().then(resp => {
        this.materialOptions = resp.data;
      });
      queryBasicName().then(resp => {
        this.basicOptions = resp.data;
      });
      queryMiddles(data).then(resp => {
        this.middle = resp.data;
        this.envData = resp.envData;
      });
    },
    del(row) {
      log.debug('row = ' + JSON.stringify(row));
      const data = {
        id: row.id
      };
      this.$confirm('此操作将删除[' + row.name + '], 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          delMiddle(data)
            .then(() => {
              this.$message.success('成功删除中级产品[' + row.name + ']');
              this.getData();
            })
            .catch(() => {
              this.$message.error('删除中级产品[' + row.name + ']失败');
            });
        })
        .catch(() => {
          log.debug('已取消删除[' + row.name + ']');
        });
    },
    add() {
      log.debug('add new in table');
      this.dialog.show = true;
      this.dialog.title = '新增中级产品';
      this.dialog.right = '新增';
      this.dialog.row = this.row;
    },
    addNewRow() {
      log.debug('add new row in dialog');
      this.$confirm('此选择要添加的类型', '提示', {
        confirmButtonText: '原材料',
        cancelButtonText: '基础产品',
        type: 'info'
      })
        .then(() => {
          this.row.formulas.push(this.$tools.createMaterial());
        })
        .catch(() => {
          this.row.formulas.push(this.$tools.createProduct());
        });
    },
    closeDialog() {
      this.dialog.show = false;
    },
    modify(row) {
      log.debug('modify current row');
      this.dialog.show = true;
      this.dialog.title = '修改';
      this.dialog.right = '保存';
      this.dialog.row = this.$tools.deepClone(row);
    },
    addOne() {
      log.debug('execute add one methods');
    },
    search(content) {
      log.debug('execute method search');
      log.debug('it will search ' + content);
      // 直接过滤数据，如果content为空的时候，表示查询所有数据，需要从数据库再次获取数据，然后显示
      if (!this.$tools.isEmpty(content)) {
        const data = {
          name: content,
          envData: this.envData
        };
        queryMiddleByName(content).then(resp => {
          const respData = resp.data;
          log.debug('respData = ' + JSON.stringify(respData));
          if (respData.length !== 0) {
            this.middle = respData;
            this.envData = resp.envData
          } else {
            this.$message.error('没有找到原材料[' + content + ']');
          }
        });
      } else {
        const data = {
          envData: this.envData
        };
        queryMiddles(data).then(resp => {
          this.middle = resp.data;
          this.envData = resp.envData;
        });
      }
    }
  }
};
</script>
<style lang="stylus" scoped></style>
