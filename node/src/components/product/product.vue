<template>
  <div class="product">
    <el-form-item v-for="(formula, index) in formulas" :key="index"
                  :label="formula.type" label-width="100px">
      <el-col :span="6">
        <el-select v-model="formula.name" placeholder="请选择"
                   v-if="show(formula)">
          <el-option v-for="item in materialOptions" :key="item.id"
                     :label="item.name" :value="item.name"></el-option>
        </el-select>
        <el-select v-model="formula.name" placeholder="请选择" v-else>
          <el-option v-for="item in productOptions" :key="item.id"
                     :label="item.name" :value="item.name"></el-option>
        </el-select>
      </el-col>
      <el-col :span="2" class="count" style="text-align:center;">
        <span>数量</span>
      </el-col>
      <el-col :span="8">
        <el-input v-model="formula.count" autocomplete="off"></el-input>
      </el-col>
      <el-col :span="8" class="buttons" style="text-align:right;">
        <el-button @click="del(index)" type="danger" size="medium"
                   icon="el-icon-remove-outline"/>
        <el-button @click="add(formula.type)" type="primary" size="medium"
                   icon="el-icon-circle-plus-outline"/>
      </el-col>
    </el-form-item>
  </div>
</template>
<script>
  import Logger from 'chivy';

  const log = new Logger('components/product/product');
  export default {
    name: 'FromProduct',
    props: {
      materialOptions: {
        type: Array
      },
      productOptions: {
        type: Array
      },
      formulas: {
        type: Array
      }
    },
    methods: {
      show(formula) {
        return formula.type === this.$tools.MaterialName;
      },
      add(index) {
        log.debug('click add button');
        this.$emit('add', index);
      },
      del(type) {
        log.debug('click del button');
        this.$emit('del', type);
      }
    }
  };
</script>
