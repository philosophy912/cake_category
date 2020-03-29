<template>
  <div class="dialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.show"
               :show-close="false">
      <Form :row="dialog.row"></Form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">{{dialog.left}}</el-button>
        <el-button type="primary" @click="confirm()">{{dialog.right}}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import { updateMaterial, addMaterial } from '@/api/materials';
  import Form from '@/components/material/form';
  import Logger from 'chivy';

  const log = new Logger('components/material/dialog');
  export default {
    name: 'MaterialDialog',
    components: {
      Form
    },
    props: {
      dialog: {
        type: Object
      }
    },
    methods: {
      cancle() {
        log.info('cancle button on');
        this.closeDialog();
      },
      confirm() {
        log.info('config button on and send data to background');
        log.debug('button value is' + JSON.stringify(this.dialog.right));
        const data = {
          name: this.dialog.row.name,
          price: this.dialog.row.price,
          capacity: this.dialog.row.capacity,
          unit: this.dialog.row.unit
        };
        if (this.dialog.right === '保存') {
          data.id = this.dialog.row.id,
            updateMaterial(data).then(() => {
              this.$message.success('更新原材料[' + data.name + ']成功');
            });
        } else if (this.dialog.right === '新增') {
          addMaterial(data).then(() => {
            this.$message.success('添加原材料[' + data.name + ']成功');
          });
        }
        this.closeDialog();
      },
      closeDialog() {
        log.info('material Dialog closeDialog');
        this.$emit('closeDialog');
      }
    }
  };
</script>
