<template>
  <div class="material">
    <MaterialTable :data="materials" @del="del" @add="addNew" @modify="modify" @search="search">
    </MaterialTable>
    <MaterialDialog :dialog="dialog" @closeDialog="closeDialog"></MaterialDialog>
    <div v-if="show">
      <MaterialForm :row="row"></MaterialForm>
      <el-button type="primary" @click="add()">新增</el-button>
    </div>
  </div>
</template>
<script>
import Logger from 'chivy';
import { queryMaterials } from '@/api/materials';
import MaterialDialog from '@/components/material/dialog.vue';
import MaterialForm from '@/components/material/form.vue';
import MaterialTable from '@/components/material/table.vue';

const log = new Logger('views/material');
export default {
  name: 'Material',
  beforeRouteEnter(to, from, next) {
    next(vm => {
      log.debug('beforeRouteEnter to path is ' + to.path);
      queryMaterials().then(resp => {
        log.debug("resp = " + JSON.stringify(resp));
        vm.materials = resp;
      });
    });
  },
  components: {
    MaterialDialog,
    MaterialForm,
    MaterialTable
  },
  computed: {
    show() {
      return this.materials.length == 0;
    }
  },
  data() {
    return {
      materials: [],
      dialog: {
        title: '',
        show: false,
        formulas: '',
        row: '',
        left: '取消',
        right: ''
      },
      row: {
        name: '',
        capacity: '',
        capacityType: '',
        price: '',
        pricePerCapacity: ''
      }
    };
  },
  methods: {
    add() {
      log.debug('add');
      this.materials.push(this.row);
    },
    del(index) {
      log.debug('delete index[' + index + ']');
      this.materials.splice(index, 1);
    },
    addNew() {
      log.debug('add new in table');
      this.dialog.show = true;
      this.dialog.title = '新增原材料';
      this.dialog.right = '新增';
      this.dialog.row = this.row;
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
    search(content) {
      log.debug('execute method search');
      log.debug('it will search ' + content);
      // 直接过滤数据，如果content为空的时候，表示查询所有数据，需要从数据库再次获取数据，然后显示
      // 做filter之前必须恢复所有数据
      this.materials = material;
      if (!this.$tools.isEmpty(content)) {
        const filterData = this.materials.filter(
          product => product.name.indexOf(content) != -1
        );
        if (filterData.length != 0) {
          this.materials = filterData;
        } else {
          this.$message.error('没有找到原材料[' + content + ']');
        }
      }
    }
  }
};
</script>