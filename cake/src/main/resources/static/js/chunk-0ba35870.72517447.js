(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0ba35870"],{"3c4a":function(t,e,a){"use strict";var n=a("91fe"),i=a("f240"),o=a("51cf"),r=a("61ef"),l=a("f30e"),c=1..toFixed,s=Math.floor,u=function(t,e,a){return 0===e?a:e%2===1?u(t,e-1,a*t):u(t*t,e/2,a)},d=function(t){var e=0,a=t;while(a>=4096)e+=12,a/=4096;while(a>=2)e+=1,a/=2;return e},f=c&&("0.000"!==8e-5.toFixed(3)||"1"!==.9.toFixed(0)||"1.25"!==1.255.toFixed(2)||"1000000000000000128"!==(0xde0b6b3a7640080).toFixed(0))||!l((function(){c.call({})}));n({target:"Number",proto:!0,forced:f},{toFixed:function(t){var e,a,n,l,c=o(this),f=i(t),h=[0,0,0,0,0,0],g="",m="0",p=function(t,e){var a=-1,n=e;while(++a<6)n+=t*h[a],h[a]=n%1e7,n=s(n/1e7)},b=function(t){var e=6,a=0;while(--e>=0)a+=h[e],h[e]=s(a/t),a=a%t*1e7},w=function(){var t=6,e="";while(--t>=0)if(""!==e||0===t||0!==h[t]){var a=String(h[t]);e=""===e?a:e+r.call("0",7-a.length)+a}return e};if(f<0||f>20)throw RangeError("Incorrect fraction digits");if(c!=c)return"NaN";if(c<=-1e21||c>=1e21)return String(c);if(c<0&&(g="-",c=-c),c>1e-21)if(e=d(c*u(2,69,1))-69,a=e<0?c*u(2,-e,1):c/u(2,e,1),a*=4503599627370496,e=52-e,e>0){p(0,a),n=f;while(n>=7)p(1e7,0),n-=7;p(u(10,n,1),0),n=e-1;while(n>=23)b(1<<23),n-=23;b(1<<n),p(1,1),b(2),m=w()}else p(0,a),p(1<<-e,0),m=w()+r.call("0",f);return f>0?(l=m.length,m=g+(l<=f?"0."+r.call("0",f-l)+m:m.slice(0,l-f)+"."+m.slice(l-f))):m=g+m,m}})},"3d2f":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"material"},[a("MaterialTable",{attrs:{data:t.materials,envData:t.envData},on:{handleSizeChange:t.handleSizeChange,handleCurrentChange:t.handleCurrentChange,del:t.del,add:t.add,modify:t.modify,search:t.search}}),a("MaterialDialog",{attrs:{dialog:t.dialog},on:{closeDialog:t.closeDialog}})],1)},i=[],o=(a("c354"),a("c4a4")),r=a.n(o),l=a("07f7"),c=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"dialog"},[a("el-dialog",{attrs:{title:t.dialog.title,visible:t.dialog.show,"show-close":!1},on:{"update:visible":function(e){return t.$set(t.dialog,"show",e)}}},[a("Form",{attrs:{row:t.dialog.row}}),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){return t.cancle()}}},[t._v(t._s(t.dialog.left))]),a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.confirm()}}},[t._v(t._s(t.dialog.right)+" ")])],1)],1)],1)},s=[],u=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"form"},[a("el-form",{attrs:{model:t.row,"status-icon":"",rules:t.rules}},[a("el-form-item",{attrs:{label:"名称","label-width":"100px",required:""}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:t.row.name,callback:function(e){t.$set(t.row,"name",e)},expression:"row.name"}})],1),a("el-form-item",{attrs:{label:"容量","label-width":"100px",required:""}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:t.row.capacity,callback:function(e){t.$set(t.row,"capacity",e)},expression:"row.capacity"}})],1),a("el-form-item",{attrs:{label:"容量单位","label-width":"100px",required:""}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:t.row.unit,callback:function(e){t.$set(t.row,"unit",e)},expression:"row.unit"}})],1),a("el-form-item",{attrs:{label:"单价","label-width":"100px",required:""}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:t.row.price,callback:function(e){t.$set(t.row,"price",e)},expression:"row.price"}})],1)],1)],1)},d=[],f=(a("3c4a"),a("4045"),a("b128"),a("b3f9"),a("7267"),new r.a("utils/validate"),function(t,e){return new RegExp(e).test(t)}),h=function(t,e,a){""===e.content?e.must?a(new Error("请输入"+e.label)):a():f(e.content,"^([1-9][0-9]*)+(.[0-9]{1,2})?$")?a():a(new Error("请输入正确的"+e.label))},g=function(t,e,a){""===e.content?e.must?a(new Error("请输入"+e.label)):a():f(e.content,"^[+]{0,1}(\\d+)$")?a():a(new Error("请输入正确的"+e.label))},m=function(t,e,a){var n=["克","千克","个","毫升","升","片"];""===e.content?e.must?a(new Error("请输入"+e.label)):a():-1==n.indexOf(e.count)?a(new Error("请输入容量单位")):a()},p=(new r.a("components/material/form"),{name:"MaterialForm",data:function(){return{rules:{name:[{required:!0,message:"请输入活动名称",trigger:"blur"}],capacity:[{validator:g,message:"请输入名称",trigger:"blur"}],capacityType:[{validator:m,trigger:"blur"}],price:[{validator:h,trigger:"blur"}]}}},methods:{pricePerCapacity:function(t,e){return this.$tools.isNotEmpty(t)&&this.$tools.isNotEmpty(e)?(t/e).toFixed(4):0}},props:{row:{type:Object}}}),b=p,w=a("e90a"),v=Object(w["a"])(b,u,d,!1,null,"865d2794",null),y=v.exports,x=new r.a("components/material/dialog"),D={name:"MaterialDialog",components:{Form:y},props:{dialog:{type:Object}},methods:{cancle:function(){x.info("cancle button on"),this.closeDialog()},confirm:function(){var t=this;x.info("config button on and send data to background"),x.debug("button value is"+JSON.stringify(this.dialog.right));var e={name:this.dialog.row.name,price:this.dialog.row.price,capacity:this.dialog.row.capacity,unit:this.dialog.row.unit};"保存"===this.dialog.right?(e.id=this.dialog.row.id,Object(l["f"])(e).then((function(){t.$message.success("更新原材料["+e.name+"]成功")}))):"新增"===this.dialog.right&&Object(l["a"])(e).then((function(){t.$message.success("添加原材料["+e.name+"]成功")})),this.closeDialog()},closeDialog:function(){x.info("material Dialog closeDialog"),this.$emit("closeDialog")}}},C=D,$=Object(w["a"])(C,c,s,!1,null,null,null),O=$.exports,E=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"table"},[t.showTable?a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.data,border:""}},[a("el-table-column",{attrs:{fixed:"",prop:"name",label:"名称"}}),a("el-table-column",{attrs:{prop:"capacity",label:"容量"}}),a("el-table-column",{attrs:{prop:"unit",label:"容量单位"}}),a("el-table-column",{attrs:{prop:"price",label:"单价"}}),a("el-table-column",{attrs:{prop:"pricePerUnit",label:"每单位价格(元)"}}),a("el-table-column",{attrs:{fixed:"right",label:"操作",width:"300"},scopedSlots:t._u([{key:"header",fn:function(e){return[a("el-input",{attrs:{size:"mini",placeholder:"输入关键字搜索"},on:{change:function(e){return t.search()}},model:{value:t.content,callback:function(e){t.content=e},expression:"content"}},[a("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:function(e){return t.search()}},slot:"append"})],1)]}},{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-edit"},on:{click:function(a){return t.modify(e.row)}}},[t._v("修改 ")]),a("el-button",{attrs:{type:"success",size:"small",icon:"el-icon-document-add"},on:{click:function(e){return t.add()}}},[t._v("新增 ")]),a("el-button",{attrs:{type:"danger",size:"small",icon:"el-icon-document-add"},on:{click:function(a){return t.del(e.row)}}},[t._v("删除 ")])]}}],null,!1,2294724305)})],1):t._e(),a("el-pagination",{attrs:{"current-page":t.envData.pageNo,"page-sizes":[5,10,20,50],"page-size":t.envData.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:t.envData.totalRows},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)},S=[],k=new r.a("components/material/table"),z={name:"MaterialTable",data:function(){return{content:""}},props:{data:{type:Array},envData:{type:Object}},computed:{showTable:function(){return 0!=this.data.length}},methods:{handleSizeChange:function(t){k.debug("每页".concat(t,"条")),this.$emit("handleSizeChange",t)},handleCurrentChange:function(t){k.debug("当前页: ".concat(t)),this.$emit("handleCurrentChange",t)},modify:function(t){k.debug("execute modify action in row"),this.$emit("modify",t)},add:function(){k.debug("execute add action in row"),this.$emit("add")},del:function(t){k.debug("execute del action in row"),this.$emit("del",t)},search:function(){k.debug("it will search content["+this.content+"]"),this.$emit("search",this.content)}}},_=z,j=Object(w["a"])(_,E,S,!1,null,"f6585da4",null),F=j.exports,N=new r.a("views/material"),M={name:"Material",mounted:function(){this.getData()},beforeRouteEnter:function(t,e,a){a((function(e){N.debug("beforeRouteEnter to path is "+t.path),e.getData()}))},components:{MaterialDialog:O,MaterialForm:y,MaterialTable:F},watch:{materials:function(t,e){0==t.length&&this.add()}},data:function(){return{materials:[],dialog:{title:"",show:!1,formulas:"",row:"",left:"取消",right:""},row:{name:"",capacity:"",unit:"克",price:"",pricePerCapacity:""},envData:{pageNo:1,pageSize:10,totalRows:0,totalPages:0}}},methods:{handleSizeChange:function(t){this.envData.pageSize=t,this.getData()},handleCurrentChange:function(t){this.envData.pageNo=t,this.getData()},getData:function(){var t=this,e={envData:this.envData};Object(l["e"])(e).then((function(e){t.materials=e.data,t.envData=e.envData}))},del:function(t){var e=this;N.debug("row = "+JSON.stringify(t));var a={id:t.id};this.$confirm("此操作将删除["+t.name+"], 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(l["b"])(a).then((function(){e.$message.success("成功删除原材料["+t.name+"]"),e.getData()})).catch((function(){e.$message.error("删除原材料["+t.name+"]失败，请检查是否有中级产品或者基础产品使用了该原材料")}))})).catch((function(){N.debug("已取消删除["+t.name+"]")}))},add:function(){N.debug("add new in table"),this.dialog.show=!0,this.dialog.title="新增原材料",this.dialog.right="新增",this.dialog.row=this.row},closeDialog:function(){this.dialog.show=!1},modify:function(t){N.debug("modify current row"),this.dialog.show=!0,this.dialog.title="修改",this.dialog.right="保存",this.dialog.row=this.$tools.deepClone(t)},search:function(t){var e=this;if(N.debug("execute method search"),N.debug("it will search "+t),this.$tools.isEmpty(t)){var a={envData:this.envData};Object(l["e"])(a).then((function(t){e.materials=t.data,e.envData=t.envData}))}else{var n={name:t,envData:this.envData};Object(l["c"])(n).then((function(a){var n=a.data;0!==n.length?(e.materials=n,e.envData=a.envData):e.$message.error("没有找到原材料["+t+"]")}))}}}},T=M,R=Object(w["a"])(T,n,i,!1,null,null,null);e["default"]=R.exports},4045:function(t,e,a){"use strict";var n=a("91fe"),i=a("6be9").indexOf,o=a("fb11"),r=a("6885"),l=[].indexOf,c=!!l&&1/[1].indexOf(1,-0)<0,s=o("indexOf"),u=r("indexOf",{ACCESSORS:!0,1:0});n({target:"Array",proto:!0,forced:c||!s||!u},{indexOf:function(t){return c?l.apply(this,arguments)||0:i(this,t,arguments.length>1?arguments[1]:void 0)}})},"51cf":function(t,e,a){var n=a("67ea");t.exports=function(t){if("number"!=typeof t&&"Number"!=n(t))throw TypeError("Incorrect invocation");return+t}},"61ef":function(t,e,a){"use strict";var n=a("f240"),i=a("3193");t.exports="".repeat||function(t){var e=String(i(this)),a="",o=n(t);if(o<0||o==1/0)throw RangeError("Wrong number of repetitions");for(;o>0;(o>>>=1)&&(e+=e))1&o&&(a+=e);return a}}}]);
//# sourceMappingURL=chunk-0ba35870.72517447.js.map