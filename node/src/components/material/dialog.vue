<template>
  <div class="dialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.show" :show-close="false">
      <Form :row="dialog.row"></Form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">{{dialog.left}}</el-button>
        <el-button type="primary" @click="confirm()">{{dialog.right}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import Form from '@/components/material/form';
import Logger from 'chivy';

const log = new Logger('components/material/dialog');
export default {
  name: 'MaterialDialog',
  components: {
    Form,
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
      this.closeDialog();
    },
    closeDialog() {
      log.info('material Dialog closeDialog');
      this.$emit('closeDialog');
    }
  }
};
</script>