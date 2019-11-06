<template>
  <div>
    <v-layout class="px-3 pb-2">
      <v-flex xs2>
        <v-btn color="info" @click="addBrand">新增品牌</v-btn>
        <v-spacer/>
      </v-flex>
      <v-spacer/>
      <v-flex xs4>
       <v-text-field label="搜索" hide-details append-icon="search" v-model="key"></v-text-field>
      </v-flex>
    </v-layout>

    <!--弹出的对话框-->
    <v-dialog max-width="500" v-model="show" persistent>
      <v-card>
        <!--对话框的标题-->
        <v-toolbar dense dark color="primary">
          <v-toolbar-title>新增品牌</v-toolbar-title>
          <!--关闭窗口的按钮-->
          <v-spacer/>
          <v-btn icon @click="closeWindow" :oldBrand="oldBrand"><v-icon>close</v-icon></v-btn>
        </v-toolbar>
        <!--对话框的内容，表单-->
        <v-card-text class="px-5">
          <!-- 表单 -->
          <brand-form :oldBrand="brand" :isEdit="isEdit" @close="show = false" :reload="getDataFromApi"/>
        </v-card-text>
      </v-card>
    </v-dialog>

    <v-data-table
      :headers="headers"
      :items="brands"
      :search="search"
      :pagination.sync="pagination"
      :total-items="totalBrands"
      :loading="loading"
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-center">{{ props.item.id }}</td>
        <td class="text-xs-center">{{ props.item.name }}</td>
        <td class="text-xs-center"><img :src="props.item.image"></td>
        <td class="text-xs-center">{{ props.item.letter }}</td>
        <td class="text-xs-center">
          <v-btn flat icon color="info" @click="editBrand(props.item)">
            <v-icon>edit</v-icon>
          </v-btn>
          <v-btn flat icon color="error" @click="deleteBrand(props.item)">
            <v-icon>delete</v-icon>
          </v-btn>
        </td>
      </template>
    </v-data-table>
  </div>
</template>
<script>
  import BrandForm from './BrandForm'
  import qs from "qs"

  export default {
    name:"MyBrand",
    components: {
      BrandForm
    },
    props:{
      oldBrand:{
        type:Object
      }
    },
    data () {
      return {
        headers: [
          { text: '品牌id',align: 'center',sortable: true,value: "id"},
          { text: '品牌名称',align: 'center',sortable: false,value: "name" },
          { text: '品牌图片地址',align: 'center',sortable: false, value: "image" },
          { text: '品牌的首字母',align: 'center',sortable: false, value: "letter" },
          { text: '操作',align: 'center',sortable: false, value: "do" },
        ],
        brands:[],
        pagination:{},
        totalBrands:0,
        loading:false,
        key:"",
        show:false,
        oldBrand:{},
      }
    },
    created(){
      this.brands=[
        {id: 1,name: "小米",image:"",letter:""},
        {id: 1,name: "小米",image:"",letter:""},
        {id: 1,name: "小米",image:"",letter:""},
        {id: 1,name: "小米",image:"",letter:""}
      ];
      this.totalBrands=15;
      //发送后台请求
      this.loadBrands;
    },
    watch:{
      key(){
        this.pagination.page=1;
        this.loadBrands();
      },
      pagination:{
        deep:true,
        handler(){
          this.loadBrands();
        }
      },
      show(val, oldVal) {
        // 表单关闭后重新加载数据
        if (oldVal) {
          this.getDataFromApi();
        }
      },
      oldBrand: {// 监控oldBrand的变化
        handler(val) {
          if(val){
            // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
            this.brand =  Object.deepCopy(val)
          }else{
            // 为空，初始化brand
            this.brand = {
              name: '',
              letter: '',
              image: '',
              categories: [],
            }
          }
        },
        deep: true
      },
    },
    methods:{
      loadBrands(){
        this.loading=true;
        this.$http.get("/item/brand/page",{
          params:{
            page:this.pagination.page,//当前页码
            rows:this.pagination.rowsPerPage,//每页大小
            sortBy:this.pagination.sortBy,//排序字段
            desc:this.pagination.descending,//
            key:this.key//搜索条件
          }
        }).then(resp=>{
          this.brands=resp.data.items;
          this.totalBrands=resp.data.total;
          this.loading=false;
        })
      },
      //修改
      editBrand(oldBrand){
        // 控制弹窗可见：
        this.show = true;
        // 获取要编辑的brand
        this.oldBrand = oldBrand;
      },
      //删除
      deleteBrand(item) {
        this.$message.confirm('此操作将永久删除该品牌, 是否继续?').then(() => {
          // 发起删除请求
          this.$http.get("/item/brand/delete?id=" + item.id,)
            .then(() => {
              // 删除成功，重新加载数据
              this.$message.success("删除成功！");
              this.$http.get("/item/brand/page");
              this.getDataFromApi();
            })
        }).catch(() => {
          this.$message.info("删除已取消！");
        });

      },
      addBrand(){
        this.brand= {};
        this.isEdit = false;
        this.show=true;
      },
      closeWindow(){
        this.show=false;
      },
      getDataFromApi() {
        this.loading = true;
        // 200ms后返回假数据
        window.setTimeout(() => {
          this.items = brandData.slice(0,4);
          this.totalItems = 100
          this.loading = false;
        }, 200)
      }
    }
  }
</script>


<style scoped>

</style>
