<template>
  <div class="table">
    <el-table :data="data" border style="width: 100%" v-if="showTable">
      <el-table-column fixed type="expand">
        <template slot-scope="scope">
          <el-table :data="scope.row.formulas" border style="width: 100%">
            <el-table-column prop="name" label="名称"></el-table-column>
            <el-table-column prop="type" label="类别"></el-table-column>
            <el-table-column prop="count" label="数量"></el-table-column>
            <el-table-column prop="price" label="总价"></el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column fixed prop="name" label="名称"></el-table-column>
      <el-table-column prop="unit" label="单位"></el-table-column>
      <el-table-column prop="capacity" label="数量"></el-table-column>
      <el-table-column prop="price" label="总价(元)"></el-table-column>
      <el-table-column fixed="right" label="操作" width="300">
        <template slot="header" slot-scope="scope">
          <el-input v-model="content" size="mini" placeholder="输入关键字搜索" @change="search(scope.row)">
            <el-button slot="append" icon="el-icon-search" @click="search(scope.row)" />
          </el-input>
        </template>
        <template slot-scope="scope">
          <el-button @click="modify(scope.row)" type="primary" size="small" icon="el-icon-edit">修改
          </el-button>
          <el-button @click="add()" type="success" size="small" icon="el-icon-document-add">新增
          </el-button>
          <el-button @click="del(scope.row)" type="danger" size="small" icon="el-icon-document-add">删除
          </el-button>
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
    del(row) {
      log.debug('click del button');
      this.$emit('del', row);
    },
    search(row) {
      log.debug('it will search content[' + this.content + ']');
      this.$emit('search', this.content);
    }
  }
};
</script>
