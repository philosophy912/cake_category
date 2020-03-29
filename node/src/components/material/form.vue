<template>
  <div class="form">
    <el-form :model="row" status-icon :rules="rules">
      <el-form-item label="名称" label-width="100px" required>
        <el-input v-model="row.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="容量" label-width="100px" required>
        <el-input v-model="row.capacity" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="容量单位" label-width="100px" required>
        <el-input v-model="row.unit" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="单价" label-width="100px" required>
        <el-input v-model="row.price" autocomplete="off"></el-input>
      </el-form-item>
      <!-- <el-form-item label="每单位价格" label-width="100px" >
        <el-input :value="pricePerCapacity(row.price, row.capacity)" autocomplete="off" :disabled="true"></el-input>
      </el-form-item> -->
    </el-form>
  </div>
</template>
<script>
  import {
    isNameValid,
    isIntegerValid,
    isCapacityTypeValid,
    isPriceValid
  } from '@/utils/validate';
  import Logger from 'chivy';

  const log = new Logger('components/material/form');
  export default {
    name: 'MaterialForm',
    data() {
      return {
        // 校验数据
        rules: {
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' }
            // { validator: isNameValid, trigger: 'blur' }
          ],
          capacity: [{
            validator: isIntegerValid,
            message: '请输入名称',
            trigger: 'blur'
          }],
          capacityType: [{ validator: isCapacityTypeValid, trigger: 'blur' }],
          price: [{ validator: isPriceValid, trigger: 'blur' }]
        }
      };
    },
    methods: {
      pricePerCapacity(price, capacity) {
        if (this.$tools.isNotEmpty(price) && this.$tools.isNotEmpty(capacity)) {
          return (price / capacity).toFixed(4);
        }
        return 0.0;
      }
    },
    props: {
      row: {
        type: Object
      }
    }
  };
</script>
<style lang="stylus" scoped></style>
