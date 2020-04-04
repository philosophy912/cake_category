<template>
  <div class="home">
    <el-container>
      <el-header>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1">原材料</el-menu-item>
          <el-menu-item index="2">基础产品</el-menu-item>
          <el-menu-item index="3">中级产品</el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Logger from 'chivy';

const log = new Logger('views/Home');
export default {
  name: 'Home',
  data() {
    return {
      activeIndex: '1',
      items: {
        '1': '原材料',
        '2': '初级产品',
        '3': '中级产品'
      }
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      if (key === '1') {
        log.debug(key);
        this.$router.push({ name: 'Material' });
      } else if (key === '2') {
        log.debug(key);
        this.$router.push({ name: 'Basic' });
      } else if (key === '3') {
        log.debug(key);
        this.$router.push({ name: 'Middle' });
      }
    }
  }
};
</script>
