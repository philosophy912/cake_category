(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0ba35870"],{"3c4a":function(t,e,i){"use strict";var n=i("91fe"),o=i("f240"),a=i("51cf"),r=i("61ef"),l=i("f30e"),c=1..toFixed,s=Math.floor,u=function(t,e,i){return 0===e?i:e%2===1?u(t,e-1,i*t):u(t*t,e/2,i)},d=function(t){var e=0,i=t;while(i>=4096)e+=12,i/=4096;while(i>=2)e+=1,i/=2;return e},f=c&&("0.000"!==8e-5.toFixed(3)||"1"!==.9.toFixed(0)||"1.25"!==1.255.toFixed(2)||"1000000000000000128"!==(0xde0b6b3a7640080).toFixed(0))||!l((function(){c.call({})}));n({target:"Number",proto:!0,forced:f},{toFixed:function(t){var e,i,n,l,c=a(this),f=o(t),m=[0,0,0,0,0,0],h="",p="0",g=function(t,e){var i=-1,n=e;while(++i<6)n+=t*m[i],m[i]=n%1e7,n=s(n/1e7)},b=function(t){var e=6,i=0;while(--e>=0)i+=m[e],m[e]=s(i/t),i=i%t*1e7},w=function(){var t=6,e="";while(--t>=0)if(""!==e||0===t||0!==m[t]){var i=String(m[t]);e=""===e?i:e+r.call("0",7-i.length)+i}return e};if(f<0||f>20)throw RangeError("Incorrect fraction digits");if(c!=c)return"NaN";if(c<=-1e21||c>=1e21)return String(c);if(c<0&&(h="-",c=-c),c>1e-21)if(e=d(c*u(2,69,1))-69,i=e<0?c*u(2,-e,1):c/u(2,e,1),i*=4503599627370496,e=52-e,e>0){g(0,i),n=f;while(n>=7)g(1e7,0),n-=7;g(u(10,n,1),0),n=e-1;while(n>=23)b(1<<23),n-=23;b(1<<n),g(1,1),b(2),p=w()}else g(0,i),g(1<<-e,0),p=w()+r.call("0",f);return f>0?(l=p.length,p=h+(l<=f?"0."+r.call("0",f-l)+p:p.slice(0,l-f)+"."+p.slice(l-f))):p=h+p,p}})},"3d2f":function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"material"},[i("MaterialTable",{attrs:{data:t.materials},on:{del:t.del,add:t.add,modify:t.modify,search:t.search}}),i("MaterialDialog",{attrs:{dialog:t.dialog},on:{closeDialog:t.closeDialog}})],1)},o=[],a=(i("c354"),i("c4a4")),r=i.n(a),l=i("07f7"),c=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"dialog"},[i("el-dialog",{attrs:{title:t.dialog.title,visible:t.dialog.show,"show-close":!1},on:{"update:visible":function(e){return t.$set(t.dialog,"show",e)}}},[i("Form",{attrs:{row:t.dialog.row}}),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(e){return t.cancle()}}},[t._v(t._s(t.dialog.left))]),i("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.confirm()}}},[t._v(t._s(t.dialog.right)+" ")])],1)],1)],1)},s=[],u=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"form"},[i("el-form",{attrs:{model:t.row,"status-icon":"",rules:t.rules}},[i("el-form-item",{attrs:{label:"名称","label-width":"100px",required:""}},[i("el-input",{attrs:{autocomplete:"off"},model:{value:t.row.name,callback:function(e){t.$set(t.row,"name",e)},expression:"row.name"}})],1),i("el-form-item",{attrs:{label:"容量","label-width":"100px",required:""}},[i("el-input",{attrs:{autocomplete:"off"},model:{value:t.row.capacity,callback:function(e){t.$set(t.row,"capacity",e)},expression:"row.capacity"}})],1),i("el-form-item",{attrs:{label:"容量单位","label-width":"100px",required:""}},[i("el-input",{attrs:{autocomplete:"off"},model:{value:t.row.unit,callback:function(e){t.$set(t.row,"unit",e)},expression:"row.unit"}})],1),i("el-form-item",{attrs:{label:"单价","label-width":"100px",required:""}},[i("el-input",{attrs:{autocomplete:"off"},model:{value:t.row.price,callback:function(e){t.$set(t.row,"price",e)},expression:"row.price"}})],1)],1)],1)},d=[],f=(i("3c4a"),i("4045"),i("b128"),i("b3f9"),i("7267"),new r.a("utils/validate"),function(t,e){return new RegExp(e).test(t)}),m=function(t,e,i){""===e.content?e.must?i(new Error("请输入"+e.label)):i():f(e.content,"^([1-9][0-9]*)+(.[0-9]{1,2})?$")?i():i(new Error("请输入正确的"+e.label))},h=function(t,e,i){""===e.content?e.must?i(new Error("请输入"+e.label)):i():f(e.content,"^[+]{0,1}(\\d+)$")?i():i(new Error("请输入正确的"+e.label))},p=function(t,e,i){var n=["克","千克","个","毫升","升","片"];""===e.content?e.must?i(new Error("请输入"+e.label)):i():-1==n.indexOf(e.count)?i(new Error("请输入容量单位")):i()},g=(new r.a("components/material/form"),{name:"MaterialForm",data:function(){return{rules:{name:[{required:!0,message:"请输入活动名称",trigger:"blur"}],capacity:[{validator:h,message:"请输入名称",trigger:"blur"}],capacityType:[{validator:p,trigger:"blur"}],price:[{validator:m,trigger:"blur"}]}}},methods:{pricePerCapacity:function(t,e){return this.$tools.isNotEmpty(t)&&this.$tools.isNotEmpty(e)?(t/e).toFixed(4):0}},props:{row:{type:Object}}}),b=g,w=i("e90a"),v=Object(w["a"])(b,u,d,!1,null,"865d2794",null),y=v.exports,x=new r.a("components/material/dialog"),$={name:"MaterialDialog",components:{Form:y},props:{dialog:{type:Object}},methods:{cancle:function(){x.info("cancle button on"),this.closeDialog()},confirm:function(){var t=this;x.info("config button on and send data to background"),x.debug("button value is"+JSON.stringify(this.dialog.right));var e={name:this.dialog.row.name,price:this.dialog.row.price,capacity:this.dialog.row.capacity,unit:this.dialog.row.unit};"保存"===this.dialog.right?(e.id=this.dialog.row.id,Object(l["f"])(e).then((function(){t.$message.success("更新原材料["+e.name+"]成功")}))):"新增"===this.dialog.right&&Object(l["a"])(e).then((function(){t.$message.success("添加原材料["+e.name+"]成功")})),this.closeDialog()},closeDialog:function(){x.info("material Dialog closeDialog"),this.$emit("closeDialog")}}},O=$,E=Object(w["a"])(O,c,s,!1,null,null,null),_=E.exports,k=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"table"},[t.showTable?i("el-table",{staticStyle:{width:"100%"},attrs:{data:t.data,border:""}},[i("el-table-column",{attrs:{fixed:"",prop:"name",label:"名称"}}),i("el-table-column",{attrs:{prop:"capacity",label:"容量"}}),i("el-table-column",{attrs:{prop:"unit",label:"容量单位"}}),i("el-table-column",{attrs:{prop:"price",label:"单价"}}),i("el-table-column",{attrs:{prop:"pricePerUnit",label:"每单位价格(元)"}}),i("el-table-column",{attrs:{fixed:"right",label:"操作",width:"300"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-edit"},on:{click:function(i){return t.modify(e.row)}}},[t._v("修改 ")]),i("el-button",{attrs:{type:"success",size:"small",icon:"el-icon-document-add"},on:{click:function(e){return t.add()}}},[t._v("新增 ")]),i("el-button",{attrs:{type:"danger",size:"small",icon:"el-icon-document-add"},on:{click:function(i){return t.del(e.row)}}},[t._v("删除 ")])]}}],null,!1,489786296)},[i("template",{slot:"header"},[i("el-input",{attrs:{size:"mini",placeholder:"输入关键字搜索"},on:{change:function(e){return t.search()}},model:{value:t.content,callback:function(e){t.content=e},expression:"content"}},[i("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:function(e){return t.search()}},slot:"append"})],1)],1)],2)],1):t._e()],1)},D=[],j=new r.a("components/material/table"),F={name:"MaterialTable",data:function(){return{content:""}},props:{data:{type:Array}},computed:{showTable:function(){return 0!=this.data.length}},methods:{modify:function(t){j.debug("execute modify action in row"),this.$emit("modify",t)},add:function(){j.debug("execute add action in row"),this.$emit("add")},del:function(t){j.debug("execute del action in row"),this.$emit("del",t)},search:function(){j.debug("it will search content["+this.content+"]"),this.$emit("search",this.content)}}},S=F,C=Object(w["a"])(S,k,D,!1,null,"dd4d14c2",null),M=C.exports,N=new r.a("views/material"),T={name:"Material",mounted:function(){this.getData()},beforeRouteEnter:function(t,e,i){i((function(e){N.debug("beforeRouteEnter to path is "+t.path),e.getData()}))},components:{MaterialDialog:_,MaterialForm:y,MaterialTable:M},watch:{materials:function(t,e){0==t.length&&this.add()}},data:function(){return{materials:[],dialog:{title:"",show:!1,formulas:"",row:"",left:"取消",right:""},row:{name:"",capacity:"",unit:"克",price:"",pricePerCapacity:""}}},methods:{getData:function(){var t=this;Object(l["e"])().then((function(e){t.materials=e}))},del:function(t){var e=this;N.debug("row = "+JSON.stringify(t));var i={id:t.id};this.$confirm("此操作将删除["+t.name+"], 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(l["b"])(i).then((function(){e.$message.success("成功删除原材料["+t.name+"]"),e.getData()})).catch((function(){e.$message.error("删除原材料["+t.name+"]失败，请检查是否有中级产品或者基础产品使用了该原材料")}))})).catch((function(){N.debug("已取消删除["+t.name+"]")}))},add:function(){N.debug("add new in table"),this.dialog.show=!0,this.dialog.title="新增原材料",this.dialog.right="新增",this.dialog.row=this.row},closeDialog:function(){this.dialog.show=!1},modify:function(t){N.debug("modify current row"),this.dialog.show=!0,this.dialog.title="修改",this.dialog.right="保存",this.dialog.row=this.$tools.deepClone(t)},search:function(t){var e=this;N.debug("execute method search"),N.debug("it will search "+t),this.$tools.isEmpty(t)?Object(l["e"])().then((function(t){N.debug("resp = "+JSON.stringify(t)),e.materials=t})):Object(l["c"])(t).then((function(i){0!==i.length?e.materials=i:e.$message.error("没有找到原材料["+t+"]")}))}}},R=T,q=Object(w["a"])(R,n,o,!1,null,null,null);e["default"]=q.exports},4045:function(t,e,i){"use strict";var n=i("91fe"),o=i("6be9").indexOf,a=i("fb11"),r=i("6885"),l=[].indexOf,c=!!l&&1/[1].indexOf(1,-0)<0,s=a("indexOf"),u=r("indexOf",{ACCESSORS:!0,1:0});n({target:"Array",proto:!0,forced:c||!s||!u},{indexOf:function(t){return c?l.apply(this,arguments)||0:o(this,t,arguments.length>1?arguments[1]:void 0)}})},"51cf":function(t,e,i){var n=i("67ea");t.exports=function(t){if("number"!=typeof t&&"Number"!=n(t))throw TypeError("Incorrect invocation");return+t}},"61ef":function(t,e,i){"use strict";var n=i("f240"),o=i("3193");t.exports="".repeat||function(t){var e=String(o(this)),i="",a=n(t);if(a<0||a==1/0)throw RangeError("Wrong number of repetitions");for(;a>0;(a>>>=1)&&(e+=e))1&a&&(i+=e);return i}}}]);
//# sourceMappingURL=chunk-0ba35870.99bf23b4.js.map