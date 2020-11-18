<template>
  <div class="table">
    <el-table :data="data" border style="width: 100%" v-if="showTable">
      <el-table-column fixed prop="name" label="名称"></el-table-column>
      <el-table-column prop="capacity" label="容量"></el-table-column>
      <el-table-column prop="unit" label="容量单位"></el-table-column>
      <el-table-column prop="price" label="单价"></el-table-column>
      <el-table-column prop="pricePerUnit" label="每单位价格(元)"></el-table-column>
      <el-table-column fixed="right" label="操作" width="300">
        <template slot="header" slot-scope="scope">
          <el-input v-model="content" size="mini" placeholder="输入关键字搜索" @change="search()">
            <el-button slot="append" icon="el-icon-search" @click="search()" />
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
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="envData.pageNo" :page-sizes="[5, 10, 20, 50]" :page-size="envData.pageSize"
      layout="total, sizes, prev, pager, next, jumper" :total="envData.totalRows">
    </el-pagination>
  </div>
</template>
<script>
import Logger from 'chivy';

const log = new Logger('components/material/table');
export default {
  name: 'MaterialTable',
  data() {
    return {
      content: ''
    };
  },
  props: {
    data: {
      type: Array
    },
    envData: {
      type: Object
    }
  },
  computed: {
    showTable() {
      return this.data.length != 0;
    }
  },
  methods: {
    handleSizeChange(val) {
      log.debug(`每页${val}条`);
      this.$emit('handleSizeChange', val)
    },
    handleCurrentChange(val) {
      log.debug(`当前页: ${val}`);
      this.$emit('handleCurrentChange', val)
    },
    modify(row) {
      log.debug('execute modify action in row');
      this.$emit('modify', row);
    },
    add() {
      log.debug('execute add action in row');
      this.$emit('add');
    },
    del(row) {
      log.debug('execute del action in row');
      this.$emit('del', row);
    },
    search() {
      log.debug('it will search content[' + this.content + ']');
      this.$emit('search', this.content);
    }
  }
};
</script>
<style lang="stylus" scoped></style>
