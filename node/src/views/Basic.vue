<template>
  <div class="basic">
    <ProductTable :data="basic" @del="del" @add="addNewBasic" @modify="modify" @search="search"></ProductTable>
    <ProductDialog :dialog="dialog" @closeDialog="closeDialog" :materialOptions="options" :productOptions="options"
      @add="addNewRow">
    </ProductDialog>
    <div v-if="show">
      <ProductForm :row="row" :materialOptions="options" :productOptions="options" :leave="true"></ProductForm>
      <el-button type="primary" @click="add()">新增</el-button>
    </div>
  </div>
</template>
<script>
import { queryBasics } from '@/api/basics';
import { queryMaterialName } from '@/api/materials';
import ProductTable from '@/components/product/table.vue';
import ProductForm from '@/components/product/form.vue';
import ProductDialog from '@/components/product/dialog.vue';
import { basicProduct, materialOptions } from '@/resources/product';
import Logger from 'chivy';

const log = new Logger('views/Basic');
export default {
  name: 'Basic',
  beforeRouteEnter(to, from, next) {
    next(vm => {
      log.debug('beforeRouteEnter to path is ' + to.path);
      queryBasics().then(resp => {
        log.debug("resp = " + JSON.stringify(resp));
        vm.basic = resp;
      });
      queryMaterialName().then(resp => {
        log.debug("resp = " + JSON.stringify(resp));
        vm.options = resp;
      })
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
        title: '',
        show: false,
        material: [],
        row: '',
        left: '取消',
        right: ''
      },
      row: this.$tools.createMaterialRow(false)
    };
  },
  computed: {
    show() {
      return this.basic.length == 0;
    }
  },
  methods: {
    add() {
      log.debug('add');
      this.basic.push(this.$tools.createMaterialRow(true));
    },
    del(index) {
      log.debug('delete index[' + index + ']');
      this.basic.splice(index, 1);
    },
    addNewBasic() {
      log.debug('add new in table');
      this.dialog.show = true;
      this.dialog.title = '新增基础产品';
      this.dialog.right = '新增';
      if (this.row.materials.length == 0) {
        this.row.materials.push(this.$tools.createMaterial());
      }
      this.dialog.row = this.row;
    },
    addNewRow() {
      log.debug('add new row in dialog');
      this.dialog.row.materials.push(this.$tools.createMaterial());
    },
    closeDialog() {
      this.dialog.show = false;
    },
    modify(row) {
      log.debug('modify current row');
      this.dialog.show = true;
      this.dialog.title = '修改';
      this.dialog.right = '保存';
      this.dialog.row = row;
    },
    search(content) {
      log.debug('execute method search');
      log.debug('it will search ' + content);
      // 直接过滤数据，如果content为空的时候，表示查询所有数据，需要从数据库再次获取数据，然后显示
      // 做filter之前必须恢复所有数据
      this.basic = basicProduct;
      if (!this.$tools.isEmpty(content)) {
        const filterData = this.basic.filter(
          product => product.name.indexOf(content) != -1
        );
        if (filterData.length != 0) {
          this.basic = filterData;
        } else {
          this.$message.error('没有找到基础产品[' + content + ']');
        }
      }
    }
  }
};
</script>