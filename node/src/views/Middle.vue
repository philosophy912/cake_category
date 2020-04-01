<template>
  <div class="basic">
    <ProductTable :data="middle" @del="del" @add="addNewBasic" @modify="modify" @search="search"></ProductTable>
    <ProductDialog :dialog="dialog" @closeDialog="closeDialog" :materialOptions="materialOptions" :productOptions="basicOptions" @add="addNewRow">
    </ProductDialog>
    <div v-if="show">
      <ProductForm :row="row" :leave="true" :materialOptions="materialOptions" :productOptions="basicOptions"></ProductForm>
      <div class="buttons">
        <el-button type="primary" @click="add()">新增</el-button>
      </div>

    </div>
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
      row: this.$tools.createProductRow(false)
    };
  },
  computed: {
    show() {
      return this.middle.length == 0;
    }
  },
  methods: {
    getData() {
      queryMaterialName().then(resp => {
        this.materialOptions = resp;
      });
      queryBasicName().then(resp => {
        this.basicOptions = resp;
      });
      queryMiddles().then(resp => {
        this.middle = resp;
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
            })
            .catch(() => {
              this.$message.error('删除中级产品[' + row.name + ']失败');
            });
        })
        .catch(() => {
          log.debug('已取消删除[' + row.name + ']')
        });
      this.getData();
    },
    add() {
      log.debug('add');
      this.middle.push(this.$tools.createProductRow(true));
    },
    addNewBasic() {
      log.debug('add new in table');
      this.dialog.show = true;
      this.dialog.title = '新增中级产品';
      this.dialog.right = '新增';
      if (this.row.formulas.length == 0) {
        this.row.formulas.push(this.$tools.createMaterial());
        this.row.formulas.push(this.$tools.createProduct());
      }

      this.dialog.row = this.row;
    },
    addNewRow() {
      log.debug('add new row in dialog');
      this.dialog.row.formulas.push(this.$tools.createMaterial());
      this.dialog.row.formulas.push(this.$tools.createProduct());
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
        queryMiddleByName(content).then(resp => {
          log.debug('resp = ' + JSON.stringify(resp));
          if (resp.length !== 0) {
            this.middle = resp;
          } else {
            this.$message.error('没有找到原材料[' + content + ']');
          }
        });
      } else {
        queryMiddles().then(resp => {
          log.debug('resp = ' + JSON.stringify(resp));
          this.middle = resp;
        });
      }
    }
  }
};
</script>
<style lang="stylus" scoped></style>
