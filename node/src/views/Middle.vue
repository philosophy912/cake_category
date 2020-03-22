<template>
  <div class="basic">
    <ProductTable :data="middle" @del="del" @add="addNewBasic" @modify="modify" @search="search"></ProductTable>
    <ProductDialog :dialog="dialog" @closeDialog="closeDialog" :materialOptions="options" :productOptions="options" @add="addNewRow">
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
import ProductTable from '@/components/product/table.vue';
import ProductForm from '@/components/product/form.vue';
import ProductDialog from '@/components/product/dialog.vue';
import { middleProduct, basicOptions, materialOptions } from '@/resources/product';
import Logger from 'chivy';

const log = new Logger('views/Middle');
export default {
  name: 'Middle',
  components: {
    ProductTable,
    ProductForm,
    ProductDialog
  },
  data() {
    return {
      middle: middleProduct,
      basicOptions: basicOptions,
      materialOptions: materialOptions,
      dialog: {
        title: '',
        show: false,
        material: [],
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
    add() {
      log.debug('add');
      this.middle.push(this.$tools.createProductRow(true));
    },
    del(index) {
      log.debug('delete index[' + index + ']');
      this.middle.splice(index, 1);
    },
    addNewBasic() {
      log.debug('add new in table');
      this.dialog.show = true;
      this.dialog.title = '新增中级产品';
      this.dialog.right = '新增';
      if (this.row.materials.length == 0) {
        this.row.materials.push(this.$tools.createMaterial());
      }
      if (this.row.products.length == 0) {
        this.row.products.push(this.$tools.createProduct());
      }
      this.dialog.row = this.row;
    },
    addNewRow() {
      log.debug('add new row in dialog');
      this.dialog.row.materials.push(this.$tools.createMaterial());
      this.dialog.row.products.push(this.$tools.createProduct());
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
    addOne() {
      log.debug('execute add one methods');
    },
    search(content) {
      log.debug('execute method search');
      log.debug('it will search ' + content);
      // 直接过滤数据，如果content为空的时候，表示查询所有数据，需要从数据库再次获取数据，然后显示
      // 做filter之前必须恢复所有数据
      this.middle = middleProduct;
      if (!this.$tools.isEmpty(content)) {
        const filterData = this.middle.filter(product => product.name.indexOf(content) != -1);
        if (filterData.length != 0) {
          this.middle = filterData;
        } else {
          this.$message.error('没有找到中级产品[' + content + ']');
        }
      }
    }
  }
};
</script>
<style lang="stylus" scoped></style>