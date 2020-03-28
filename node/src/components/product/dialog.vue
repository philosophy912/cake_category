<template>
  <div class='productDialog'>
    <el-dialog :title='dialog.title' :visible.sync='dialog.show' :show-close='false'>
      <ProductForm :row='dialog.row' :dialogType='dialog.type' @add='add' :materialOptions='materialOptions' :productOptions='productOptions' :leave='true'>
      </ProductForm>
      <div slot='footer' class='dialog-footer'>
        <el-button v-if='show' @click='add()' type='success' icon='el-icon-document-add'>新增</el-button>
        <el-button @click='cancle()'>{{dialog.left}}</el-button>
        <el-button type='primary' @click='confirm()'>{{dialog.right}}</el-button>
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
      // debugger;
      if (this.$tools.isNotEmpty(this.dialog.row)) {
        const type = this.dialog.type;
        const materials = this.dialog.row.formulas.filter(formula => {
          // log.info('formula is ' + JSON.stringify(formula));
          return formula.type === this.$tools.MaterialName;
        });
        log.info('materials = ' + JSON.stringify(materials));
        const products = this.dialog.row.formulas.filter(formula => {
          return formula.type === this.$tools.BasicName;
        });
        log.info('products = ' + JSON.stringify(products));
        if (type === this.$tools.BasicName && materials.length === 0) {
          return true;
        } else if (type === this.$tools.MiddleName) {
          if (materials.length === 0 || products.length === 0) {
            return true;
          }
        }
      }
      return false;
    }
  },
  methods: {
    cancle() {
      log.info('cancle button on');
      this.closeDialog();
    },
    confirm() {
      log.info('config button on and send data to background');
      // 保存或者更新，而且需要判断是在基础产品还是在中级产品的时候做的操作
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