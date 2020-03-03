<template>
  <div class="productDialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.show" :show-close="false">
      <ProductForm :row="dialog.row" @add="add" :materialOptions="materialOptions" :productOptions="productOptions" :leave="true">
      </ProductForm>
      <div slot="footer" class="dialog-footer">
        <el-button v-if="show" @click="add()" type="success" icon="el-icon-document-add">新增</el-button>
        <el-button @click="cancle()">{{dialog.left}}</el-button>
        <el-button type="primary" @click="confirm()">{{dialog.right}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import ProductForm from '@/components/product/form';
import Logger from 'chivy';

const log = new Logger('components/product/dialog');
export default {
  name: 'ProductDialog',
  components: {
    ProductForm
  },
  props: {
    materialOptions: {
      type: Array
    },
    productOptions: {
      type: Array
    },
    dialog: {
      type: Object
    }
  },
  computed: {
    show() {
      // 可以允许products不在row中
      const hasMaterials = this.dialog.row.hasOwnProperty('materials');
      const hasProducts = this.dialog.row.hasOwnProperty('products');
      if (!hasMaterials) {
        return true;
      } else {
        if (hasProducts) {
          return this.dialog.row.products.length == 0;
        } else if (this.dialog.row.materials.length == 0) {
          return true;
        } else {
          return false;
        }
      }
    }
  },
  methods: {
    cancle() {
      log.info('cancle button on');
      this.closeDialog();
    },
    confirm() {
      log.info('config button on and send data to background');
      this.closeDialog();
    },
    closeDialog() {
      log.info('material Dialog closeDialog');
      this.$emit('closeDialog');
    },
    add() {
      log.info('click add button');
      this.$emit('add');
    }
  }
};
</script>