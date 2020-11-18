<template>
  <div class="material">
    <MaterialTable :data="materials" :envData="envData" @handleSizeChange="handleSizeChange" @handleCurrentChange="handleCurrentChange" @del="del" @add="add" @modify="modify" @search="search">
    </MaterialTable>
    <MaterialDialog :dialog="dialog" @closeDialog="closeDialog"></MaterialDialog>
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
  watch: {
    materials(newVal, oldVal) {
      if (newVal.length == 0) {
        this.add();
      }
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
        unit: '克',
        price: '',
        pricePerCapacity: ''
      },
      envData: {
        pageNo: 1,
        pageSize: 10,
        totalRows: 0,
        totalPages: 0
      }
    };
  },
  methods: {
    handleSizeChange(val) {
      this.envData.pageSize = val;
      this.getData();
    },
    handleCurrentChange(val) {
      this.envData.pageNo = val;
      this.getData();
    },
    getData() {
      const data = {
        envData: this.envData
      };
      queryMaterials(data).then(resp => {
        this.materials = resp.data;
        this.envData = resp.envData;
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
          delMaterial(data)
            .then(() => {
              this.$message.success('成功删除原材料[' + row.name + ']');
              this.getData();
            })
            .catch(() => {
              this.$message.error('删除原材料[' + row.name + ']失败，请检查是否有中级产品或者基础产品使用了该原材料');
            });
        })
        .catch(() => {
          log.debug('已取消删除[' + row.name + ']');
        });
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
        const data = {
          name: content,
          envData: this.envData
        };
        queryMaterialByName(data).then(resp => {
          const respData = resp.data
          if (respData.length !== 0) {
            this.materials = respData;
            this.envData = resp.envData
          } else {
            this.$message.error('没有找到原材料[' + content + ']');
          }
        });
      } else {
        const data = {
          envData: this.envData
        };
        queryMaterials(data).then(resp => {
          this.materials = resp.data;
          this.envData = resp.envData;
        });
      }
    }
  }
};
</script>
