<template>
  <div class="material">
    <MaterialTable :data="materials" @del="del" @add="add" @modify="modify" @search="search">
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
import { queryMaterials, queryMaterialByName, delMaterial } from '@/api/materials';
import MaterialDialog from '@/components/material/dialog.vue';
import MaterialForm from '@/components/material/form.vue';
import MaterialTable from '@/components/material/table.vue';

const log = new Logger('views/material');
export default {
  name: 'Material',
  mounted() {
    this.getData();
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      log.debug('beforeRouteEnter to path is ' + to.path);
      vm.getData();
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
    getData() {
      queryMaterials().then(resp => {
        this.materials = resp;
      });
    },
    del(row) {
      log.debug('row = ' + JSON.stringify(row));
      const data = {
        id: row.id
      };
      this.$confirm('此操作将删除[' + row.name + '], 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          delBasic(data)
            .then(() => {
              this.$message.success('成功删除原材料[' + row.name + ']');
            })
            .catch(() => {
              this.$message.error('删除原材料[' + row.name + ']失败');
            });
        })
        .catch(() => {
          this.$message.info('已取消删除[' + row.name + ']');
        });
      this.getData();
    },
    add() {
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
      this.dialog.row = this.$tools.deepClone(row);
    },
    search(content) {
      log.debug('execute method search');
      log.debug('it will search ' + content);
      // 直接过滤数据，如果content为空的时候，表示查询所有数据，需要从数据库再次获取数据，然后显示
      if (!this.$tools.isEmpty(content)) {
        queryMaterialByName(content).then(resp => {
          if (resp.length !== 0) {
            this.materials = resp;
          } else {
            this.$message.error('没有找到原材料[' + content + ']');
          }
        });
      } else {
        queryMaterials().then(resp => {
          log.debug('resp = ' + JSON.stringify(resp));
          this.materials = resp;
        });
      }
    }
  }
};
</script>
