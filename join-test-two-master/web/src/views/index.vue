<template>
  <main role="main">

    <section class="jumbotron text-center">
      <div class="container">
        <h1>在线视频课程平台</h1>
        <p class="lead text-muted m-3">
          知识付费时代刚刚起步，在这个领域有很多的发展机会。整个课程以实战为基础，手把手从零开始，一步一步搭建一个完整的企业级开发架构。不讲废话，只讲干货。
        </p>
        <p>
          <router-link to="/list" class="btn btn-primary my-2 p-3 font-weight-bold">点击进入所有课程</router-link>
        </p>
      </div>
    </section>
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="title1">最新上线</div>
        <div class="row">
          <div v-for="o in news" class="col-md-4">
            <the-course v-bind:course="o"></the-course>
          </div> 
        </div>
        <hr>
<!-- 好课推荐与 最新上线显示的是相同的数据 根据需要后端可以写新的接口 -->
        <div class="title2">好课推荐</div>
        <div class="row">
          <div v-for="o in news" class="col-md-4">
            <the-course v-bind:course="o"></the-course>
          </div>
        </div>
      </div>
    </div>

  </main>
</template>
<script>
import TheCourse from "../components/the-course";
  export default {
    name: 'index',
    components: {TheCourse},
    data: function () {
      return {
        news: [],
      }
    },
    mounted() {
      let _this = this;
      _this.listNew();
    },
    methods: {
      /**
       * 查询新上好课
       */
      listNew() {
        let _this = this;
        //频繁读取 有少有变化的数据就可以缓存起来
        //先到缓存中度数数据 缓存更新的时间默认应该是30分钟
        //关闭浏览器缓存就销毁了
        let news = SessionStorage.get("news");
        if(!Tool.isEmpty(news)){
          _this.news = news;
          return;
        }

        _this.$ajax.get('http://localhost:9002/business/web/course/list-new').then((response)=>{
          console.log("查询新上好课结果：", response);
          let resp = response.data;
          if (resp.success) {
            _this.news = resp.content;
            //读取数据成功后 把数据添加到缓存中
            SessionStorage.set("news",_this.news);
          }
        }).catch((response)=>{
          console.log("error：", response);
        })
      },
    }
  }
</script>

<style>
  .title1{
    margin-bottom: 2rem;
    color: #fafafa;
    letter-spacing: 0;
    text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px 4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333, 0px 8px 7px #001135;
    font-size: 2rem;
  }
  .title2{
    margin-bottom: 2rem;
    color: transparent;
    -webkit-text-stroke: 1px black;
    letter-spacing: 0.04em;
    font-size: 2rem;
  }
</style>
