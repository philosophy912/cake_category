<template>
  <div class="product">
    <el-form-item v-for="(material, index) in materials" :key="index" :label="material.type" label-width="100px">
      <el-col :span="6">
        <el-select v-model="material.id" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-col>
      <el-col :span="2" class="count" style="text-align:center;">
        <span>数量</span>
      </el-col>
      <el-col :span="8">
        <el-input v-model="material.count" autocomplete="off"></el-input>
      </el-col>
      <el-col :span="8" class="buttons" style="text-align:right;">
        <el-button @click="del(index)" type="danger" size="medium" icon="el-icon-remove-outline" />
        <el-button @click="add(index)" type="primary" size="medium" icon="el-icon-circle-plus-outline" />
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
    options: {
      type: Array
    },
    materials: {
      type: Array
    }
  },
  methods: {
    add(index) {
      log.debug('click add button');
      this.$emit('add', index);
    },
    del(index) {
      log.debug('click del button');
      this.$emit('del', index);
    }
  }
};
</script>