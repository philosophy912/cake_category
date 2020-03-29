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
      <Product :formulas="row.formulas" :materialOptions="materialOptions"
               :productOptions="productOptions" @add="add" @del="del"></Product>
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
      },
      dialogType: {
        type: String
      }
    },
    computed: {
      materials() {
        return this.row.formulas.filter(formula => {
          return formula.type === this.$tools.MaterialName;
        });
      },
      products() {
        return this.row.formulas.filter(formula => {
          return formula.type === this.$tools.BasicName;
        });
      }
    },
    methods: {
      del(index) {
        this.row.formulas.splice(index, 1);
        log.debug('materials length is ' + JSON.stringify(this.row.formulas.length));
        // 无论如何都要检查原材料是否被删成了0
        if (this.leave && this.materials.length === 0) {
          this.row.formulas.push(this.$tools.createMaterial());
        }
        if (this.dialogType === this.$tools.MiddleName) {
          // 高级产品
          if (this.leave && this.products.length == 0) {
            this.row.formulas.push(this.$tools.createProduct());
          }
        }
      },
      add(type) {
        log.debug('type is ' + JSON.stringify(type));
        // 在原材料上添加的
        if (type === this.$tools.MaterialName) {
          this.row.formulas.push(this.$tools.createMaterial());
        } else if (type === this.$tools.BasicName) {
          this.row.formulas.push(this.$tools.createProduct());
        }
      }
    }
  };
</script>
