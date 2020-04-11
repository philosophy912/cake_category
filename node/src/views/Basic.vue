<template>
  <div class="basic">
    <ProductTable :data="basic" :envData="envData" @handleSizeChange="handleSizeChange" @handleCurrentChange="handleCurrentChange" @del="del" @add="add" @modify="modify" @search="search" />
    <ProductDialog :dialog="dialog" @closeDialog="closeDialog" :materialOptions="options" :productOptions="options" @add="addNewRow" />
  </div>
</template>
<script>
import { queryBasics, queryBasicByName, delBasic } from '@/api/basics';
import { queryMaterialName } from '@/api/materials';
import ProductTable from '@/components/product/table.vue';
import ProductForm from '@/components/product/form.vue';
import ProductDialog from '@/components/product/dialog.vue';
import Logger from 'chivy';

const log = new Logger('views/Basic');
export default {
  name: 'Basic',
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
      basic: [],
      options: [],
      dialog: {
        type: this.$tools.BasicName,
        title: '',
        show: false,
        row: '',
        left: '取消',
        right: ''
      },
      row: this.$tools.createMaterialRow(false),
      envData: {
        pageNo: 1,
        pageSize: 10,
        totalRows: 0,
        totalPages: 0
      }
    };
  },
  watch: {
    basic(newVal, oldVal) {
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
      queryBasics(data).then(resp => {
        this.basic = resp.data;
        this.envData = resp.envData;
      });
      queryMaterialName().then(resp => {
        this.options = resp.data;
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
          delBasic(data)
            .then(() => {
              this.$message.success('成功删除基础产品[' + row.name + ']');
              this.getData();
            })
            .catch(() => {
              this.$message.error('删除基础产品[' + row.name + ']失败，请检查是否有中级产品使用了该基础产品');
            });
        })
        .catch(() => {
          log.debug('已取消删除[' + row.name + ']');
        });
    },
    add() {
      log.debug('add new in table');
      this.dialog.show = true;
      this.dialog.title = '新增基础产品';
      this.dialog.right = '新增';
      if (this.row.formulas.length == 0) {
        this.row.formulas.push(this.$tools.createMaterial());
      }
      this.dialog.row = this.row;
    },
    addNewRow() {
      log.debug('add new row in dialog');
      this.dialog.row.formulas.push(this.$tools.createMaterial());
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
    search(content) {
      log.debug('execute method search');
      log.debug('it will search ' + content);
      // 直接过滤数据，如果content为空的时候，表示查询所有数据，需要从数据库再次获取数据，然后显示
      if (!this.$tools.isEmpty(content)) {
        const data = {
          name: content,
          envData: this.envData
        };
        queryBasicByName(data).then(resp => {
          const respData = resp.data;
          if (respData.length !== 0) {
            this.basic = respData;
            this.envData = resp.envData
          } else {
            this.$message.error('没有找到基础产品[' + content + ']');
          }
        });
      } else {
        const data = {
          envData: this.envData
        };
        queryBasics(data).then(resp => {
          this.basic = resp.data;
          this.envData = resp.envData;
        });
      }
    }
  }
};
</script>
