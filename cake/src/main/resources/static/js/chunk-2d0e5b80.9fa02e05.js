(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0e5b80"],{"964a":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"basic"},[a("ProductTable",{attrs:{data:t.middle,envData:t.envData},on:{handleSizeChange:t.handleSizeChange,handleCurrentChange:t.handleCurrentChange,del:t.del,add:t.add,modify:t.modify,search:t.search}}),a("ProductDialog",{attrs:{dialog:t.dialog,materialOptions:t.materialOptions,productOptions:t.basicOptions},on:{closeDialog:t.closeDialog,add:t.addNewRow}})],1)},i=[],o=(a("c354"),a("8a8e")),d=a("f98d"),s=a("07f7"),c=a("80d7"),r=a("2688"),h=a("df3a"),l=a("c4a4"),u=a.n(l),g=new u.a("views/Middle"),f={name:"Middle",mounted:function(){this.getData()},beforeRouteEnter:function(t,e,a){a((function(e){g.debug("beforeRouteEnter to path is "+t.path),e.getData()}))},components:{ProductTable:c["a"],ProductForm:r["a"],ProductDialog:h["a"]},data:function(){return{middle:[],basicOptions:[],materialOptions:[],dialog:{type:this.$tools.MiddleName,title:"",show:!1,row:"",left:"取消",right:""},row:this.$tools.createBothRow(!0),envData:{pageNo:1,pageSize:10,totalRows:0,totalPages:0}}},watch:{middle:function(t,e){0==t.length&&this.add()}},methods:{handleSizeChange:function(t){this.envData.pageSize=t,this.getData()},handleCurrentChange:function(t){this.envData.pageNo=t,this.getData()},getData:function(){var t=this,e={envData:this.envData};Object(s["d"])().then((function(e){t.materialOptions=e.data})),Object(d["d"])().then((function(e){t.basicOptions=e.data})),Object(o["d"])(e).then((function(e){t.middle=e.data,t.envData=e.envData}))},del:function(t){var e=this;g.debug("row = "+JSON.stringify(t));var a={id:t.id};this.$confirm("此操作将删除["+t.name+"], 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(o["b"])(a).then((function(){e.$message.success("成功删除中级产品["+t.name+"]"),e.getData()})).catch((function(){e.$message.error("删除中级产品["+t.name+"]失败")}))})).catch((function(){g.debug("已取消删除["+t.name+"]")}))},add:function(){g.debug("add new in table"),this.dialog.show=!0,this.dialog.title="新增中级产品",this.dialog.right="新增",this.dialog.row=this.row},addNewRow:function(){var t=this;g.debug("add new row in dialog"),this.$confirm("此选择要添加的类型","提示",{confirmButtonText:"原材料",cancelButtonText:"基础产品",type:"info"}).then((function(){t.row.formulas.push(t.$tools.createMaterial())})).catch((function(){t.row.formulas.push(t.$tools.createProduct())}))},closeDialog:function(){this.dialog.show=!1},modify:function(t){g.debug("modify current row"),this.dialog.show=!0,this.dialog.title="修改",this.dialog.right="保存",this.dialog.row=this.$tools.deepClone(t)},addOne:function(){g.debug("execute add one methods")},search:function(t){var e=this;if(g.debug("execute method search"),g.debug("it will search "+t),this.$tools.isEmpty(t)){var a={envData:this.envData};Object(o["d"])(a).then((function(t){e.middle=t.data,e.envData=t.envData}))}else{this.envData;Object(o["c"])(t).then((function(a){var n=a.data;g.debug("respData = "+JSON.stringify(n)),0!==n.length?(e.middle=n,e.envData=a.envData):e.$message.error("没有找到原材料["+t+"]")}))}}}},m=f,w=a("e90a"),b=Object(w["a"])(m,n,i,!1,null,"575badda",null);e["default"]=b.exports}}]);
//# sourceMappingURL=chunk-2d0e5b80.9fa02e05.js.map