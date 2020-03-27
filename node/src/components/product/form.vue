<template>
  <div class="productForm">
    <el-form :model="row">
      <el-form-item label="名称" label-width="100px" required>
        <el-input v-model="row.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="数量" label-width="100px" required>
        <el-input v-model="row.capacity" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="单位" label-width="100px" required>
        <el-input v-model="row.unit" autocomplete="off"></el-input>
      </el-form-item>
      <!-- <el-form-item label="总价" label-width="100px">
        <el-input :value="totalPrice(row)" autocomplete="off" :disabled="true"></el-input>
      </el-form-item> -->
      <Product :materials="materials" :options="materialOptions" @add="addMaterial" @del="delMaterial"></Product>
      <Product v-if="show" :materials="products" :options="productOptions" @del="delProduct" @add="addProduct">
      </Product>
    </el-form>
  </div>
</template>
<script>
import Product from '@/components/product/product.vue';
import Logger from 'chivy';

const log = new Logger('components/product/form');
export default {
  name: 'ProductFrom',
  components: {
    Product
  },
  data() {
    return {};
  },
  props: {
    row: {
      type: Object
    },
    materialOptions: {
      type: Array
    },
    productOptions: {
      type: Array
    },
    leave: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    show() {
      return this.products.length !== 0;
    },
    materials() {
      const materialArrays = [];
      this.row.formulas.forEach(formula => {
        if (formula.type === this.$tools.MaterialName) {
          materialArrays.push(formula);
        }
      });
      return materialArrays;
    },
    products() {
      const productArrays = [];
      this.row.formulas.forEach(formula => {
        if (formula.type === this.$tools.BasicName) {
          productArrays.push(formula);
        }
      });
      return productArrays;
    }
  },
  methods: {
    addMaterial(index) {
      log.debug('add material');
      this.row.formulas.push(this.$tools.createMaterial());
    },
    addProduct(index) {
      log.debug('add product');
      this.row.formulas.push(this.$tools.createProduct());
    },
    delMaterial(index) {
      this.row.formulas.splice(index, 1);
      log.debug('materials length is ' + JSON.stringify(this.row.materials.length));
      if (this.leave && this.row.materials.length == 0) {
        log.debug('materials is empty, now add new one');
        this.row.materials.push(this.$tools.createMaterial());
      }
    },
    delProduct(index) {
      this.row.products.splice(index, 1);
      log.debug('products length is ' + JSON.stringify(this.row.products.length));
      if (this.leave && this.row.products.length == 0) {
        log.debug('products is empty, now add new one');
        this.row.products.push(this.$tools.createProduct());
      }
    },
    totalPrice(row) {
      var price = 0.0;
      // log.debug('row is ' + JSON.stringify(row));
      // 遍历products和materials
      row.materials.forEach(material => {
        if (this.$tools.isNotEmpty(material.price) && this.$tools.isNotEmpty(material.count)) {
          price += material.price * material.count;
        }
      });
      if (row.hasOwnProperty('products')) {
        row.products.forEach(product => {
          if (this.$tools.isNotEmpty(product.price) && this.$tools.isNotEmpty(product.count)) {
            price += product.price * product.count;
          }
        });
      }
      return price;
    }
  }
};
</script>