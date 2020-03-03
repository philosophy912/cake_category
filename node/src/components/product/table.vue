<template>
  <div class="table">
    <el-table :data="tableData" border style="width: 100%" v-if="showTable">
      <el-table-column fixed type="expand">
        <template slot-scope="scope">
          <el-table :data="scope.row.all" border style="width: 100%">
            <el-table-column prop="name" label="名称"></el-table-column>
            <el-table-column prop="type" label="类别"></el-table-column>
            <el-table-column prop="count" label="数量"></el-table-column>
            <el-table-column prop="price" label="单价"></el-table-column>
            <el-table-column prop="totalPrice" label="总价"></el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column fixed prop="name" label="名称"></el-table-column>
      <el-table-column prop="unit" label="单位"></el-table-column>
      <el-table-column prop="count" label="总量"></el-table-column>
      <el-table-column prop="totalPrice" label="总价"></el-table-column>
      <el-table-column fixed="right" label="操作" width="300">
        <template slot="header" slot-scope="scope">
          <el-input v-model="content" size="mini" placeholder="输入关键字搜索" @change="search(scope.row)">
            <el-button slot="append" icon="el-icon-search" @click="search(scope.row)" />
          </el-input>
        </template>
        <template slot-scope="scope">
          <el-button @click="modify(scope.row)" type="primary" size="small" icon="el-icon-edit">修改</el-button>
          <el-button @click="add()" type="success" size="small" icon="el-icon-document-add">新增</el-button>
          <el-button @click="del(scope.$index)" type="danger" size="small" icon="el-icon-document-add">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import Logger from 'chivy';

const log = new Logger('components/product/table');
export default {
  name: 'ProductTable',
  data() {
    return {
      content: ''
    };
  },
  props: {
    data: {
      type: Array
    }
  },
  computed: {
    showTable() {
      return this.data.length != 0;
    },
    tableData() {
      const tableData = [];
      this.data.forEach(data => {
        data.all = [];
        data.materials.forEach(material => {
          data.all.push(material);
        });
        if (data.hasOwnProperty('products')) {
          data.products.forEach(product => {
            data.all.push(product);
          });
        }
        tableData.push(data);
      });
      return tableData;
    }
  },
  methods: {
    modify(row) {
      log.debug('click modify button');
      this.$emit('modify', row);
    },
    add() {
      log.debug('click add button');
      this.$emit('add');
    },
    del(index) {
      log.debug('click add button');
      this.$emit('del', index);
    },
    search(row) {
      log.debug('it will search content[' + this.content + ']');
      this.$emit('search', this.content);
    }
  }
};
</script>