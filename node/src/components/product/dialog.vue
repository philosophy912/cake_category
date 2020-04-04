<template>
  <div class="productDialog">
    <el-dialog :title="dialog.title" :visible.sync="dialog.show" :show-close="false">
      <ProductForm
        :row="dialog.row"
        :dialogType="dialog.type"
        @add="add"
        :materialOptions="materialOptions"
        :productOptions="productOptions"
        :leave="true"
      ></ProductForm>
      <div slot="footer" class="dialog-footer">
        <el-button @click="add()" type="success" icon="el-icon-document-add">新增配方</el-button>
        <el-button @click="cancle()">{{dialog.left}}</el-button>
        <el-button type="primary" @click="confirm()">{{dialog.right}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { updateBasic, addBasic } from '@/api/basics';
import { updateMiddle, addMiddle } from '@/api/middles';
import ProductForm from '@/components/product/form';
import Logger from 'chivy';

const log = new Logger('components/product/dialog');
export default {
  name: 'ProductDialog',
  components: {
    ProductForm
  },
  props: {
    materialOptions: {
      type: Array
    },
    productOptions: {
      type: Array
    },
    dialog: {
      type: Object
    }
  },
  methods: {
    cancle() {
      log.info('cancle button on');
      this.closeDialog();
    },
    getUpdateData() {
      const formulas = [];
      this.dialog.row.formulas.forEach(formula => {
        const vo = {
          fid: formula.fid,
          count: formula.count,
          type: formula.type,
          id: formula.id,
          pid: formula.pid
        };
        formulas.push(vo);
      });
      log.debug('formulas is ' + JSON.stringify(formulas));
      const data = {
        id: this.dialog.row.id,
        name: this.dialog.row.name,
        unit: this.dialog.row.unit,
        capacity: this.dialog.row.capacity,
        formulas: formulas
      };
      log.debug('data is ' + JSON.stringify(data));
      return data;
    },
    getAddData() {
      const formulas = [];
      this.dialog.row.formulas.forEach(formula => {
        const vo = {
          count: formula.count,
          type: formula.type,
          id: formula.id
        };
        formulas.push(vo);
      });
      log.debug('formulas is ' + JSON.stringify(formulas));
      const data = {
        name: this.dialog.row.name,
        unit: this.dialog.row.unit,
        capacity: this.dialog.row.capacity,
        formulas: formulas
      };
      log.debug('data is ' + JSON.stringify(data));
      return data;
    },
    confirm() {
      log.info('config button on and send data to background');
      // todo 在正在保存之前需要校验是否正确
      // 保存或者更新，而且需要判断是在基础产品还是在中级产品的时候做的操作
      const type = this.dialog.type;
      const action = this.dialog.right;
      if (type === this.$tools.BasicName) {
        // 基础产品的情况下
        if (action === this.$tools.saveButton) {
          // 更新
          const data = this.getUpdateData();
          updateBasic(data).then(() => {
            this.$message.success('更新中级材料[' + data.name + ']成功');
            this.$parent.getData();
          });
        } else if (action === this.$tools.addButton) {
          // 新增
          const data = this.getAddData();
          addBasic(data).then(() => {
            this.$message.success('添加中级材料[' + data.name + ']成功');
            this.$parent.getData();
          });
        }
      } else if (type === this.$tools.MiddleName) {
        // 中级产品的情况下
        if (action === this.$tools.saveButton) {
          // 更新
          const data = this.getUpdateData();
          updateMiddle(data).then(() => {
            this.$message.success('更新中级材料[' + data.name + ']成功');
            this.$parent.getData();
          });
        } else if (action === this.$tools.addButton) {
          // 新增
          const data = this.getAddData();
          addMiddle(data).then(() => {
            this.$message.success('添加中级材料[' + data.name + ']成功');
            this.$parent.getData();
          });
        }
      }
      this.closeDialog();
    },
    closeDialog() {
      log.info('material Dialog closeDialog');
      this.$emit('closeDialog');
    },
    add() {
      log.info('click add button');
      this.$emit('add');
    }
  }
};
</script>
