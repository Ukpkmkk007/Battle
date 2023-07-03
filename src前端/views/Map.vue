<!--<template>-->
<!--    <div>-->
<!--        <div id="container" style="width: 100%; height: calc(100vh - 100px)"></div>-->
<!--        <div id="info"></div>-->
<!--    </div>-->
<!--</template>-->

<!--<script>-->

<!--    var content = [-->
<!--        "<div style='font-size: 14px; color: red; width: 200px; height: 50px'>这是信息窗口</div>"-->
<!--    ];-->

<!--    export default {-->
<!--        name: "Map",-->
<!--        mounted() {-->
<!--            // 创建地图实例-->
<!--            var map = new AMap.Map("container", {-->
<!--                zoom: 13,-->
<!--                resizeEnable: true,-->
<!--            })-->

<!--            var infoWindow = new AMap.InfoWindow({-->
<!--                anchor: 'top-right',-->
<!--                content: content.join("<br>")  //传入 dom 对象，或者 html 字符串-->
<!--            });-->

<!--            var clickHandler = function (e) {-->
<!--                console.log('您在[ ' + e.lnglat.getLng() + ',' + e.lnglat.getLat() + ' ]的位置点击了地图！');-->
<!--                infoWindow.open(map, [126.685649, 45.779939]);-->
<!--            };-->
<!--            var marker = new AMap.Marker({-->
<!--                position: new AMap.LngLat(126.685649, 45.779939),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]-->
<!--                title: '哈尔滨工程大学',-->
<!--                icon: '//vdata.amap.com/icons/b18/1/2.png',-->
<!--            })-->
<!--            marker.on('click', clickHandler);-->
<!--            // 将创建的点标记添加到已有的地图示例-->
<!--            map.add(marker);-->

<!--            // path 是驾车导航的起、途径和终点，最多支持16个途经点-->
<!--            var path = []-->

<!--            path.push([126.656681, 45.795415])-->
<!--            path.push([126.682553, 45.771883])-->
<!--            path.push([126.702603, 45.791997])-->

<!--            map.plugin('AMap.DragRoute', function () {-->
<!--                var route = new AMap.DragRoute(map, path, AMap.DrivingPolicy.LEAST_FEE)-->
<!--                // 查询导航路径并开启拖拽导航-->
<!--                route.search()-->
<!--            })-->

<!--            var polyLine = new AMap.Polyline({-->
<!--                path: path,-->
<!--                strokeWeight: 5,-->
<!--                borderWeight: 5, // 线条宽度，默认为 1-->
<!--                strokeStyle: "solid",-->
<!--                strokeColor: '#DC143C', // 线条颜色-->
<!--                lineJoin: 'round' // 折线拐点连接处样式-->
<!--            })-->
<!--            map.add(polyLine)-->

<!--            // 实例化城市查询类-->
<!--            map.plugin('AMap.Geolocation', function () {-->
<!--                var geolocation = new AMap.Geolocation({-->
<!--                    // 是否使用高精度定位，默认：true-->
<!--                    enableHighAccuracy: true,-->
<!--                    // 设置定位超时时间，默认：无穷大-->
<!--                    timeout: 10000,-->
<!--                    // 定位按钮的停靠位置的偏移量-->
<!--                    offset: [10, 20],-->
<!--                    //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false-->
<!--                    zoomToAccuracy: true,-->
<!--                    //  定位按钮的排放位置,  RB表示右下-->
<!--                    position: 'RB'-->
<!--                })-->

<!--                geolocation.getCurrentPosition(function (status, result) {-->
<!--                    if (status == 'complete') {-->
<!--                        onComplete(result)-->
<!--                    } else {-->
<!--                        onError(result)-->
<!--                    }-->
<!--                });-->

<!--                function onComplete(data) {-->
<!--                    // data是具体的定位信息-->
<!--                    console.log(data)-->
<!--                }-->

<!--                function onError(data) {-->
<!--                    // 定位出错-->
<!--                    console.error(data)-->
<!--                }-->
<!--            })-->

<!--        }-->
<!--    }-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->

<template>
  <div style="padding: 10px">
    <el-card>
      <div v-for="item in videos" :key="item.id" style="margin: 10px 0; padding: 10px 0; color: #666; border-bottom: 1px dashed #ccc">
        <span style="font-size: 14px; cursor: pointer" class="item" @click="detail(item.id)">{{ item.name }}</span>
        <span style="float: right; font-size: 12px; margin-top: 10px">文件大小：{{ item.size }} kb</span>
      </div>
    </el-card>

    <el-card>
      <div class='demo'>
        <video-player class="video-player-box"
                      ref="videoPlayer"
                      :playsinline="true"
                      :options="playerOptions">
        </video-player>
      </div>
    </el-card>
  </div>


</template>

<script>

import 'video.js/dist/video-js.css'
import { videoPlayer } from 'vue-video-player'

export default {
  name: "Video",
  components: {
    videoPlayer
  },
  data() {
    return {
      videos: [],
      playerOptions: {
        playbackRates: [0.5, 1.0, 1.5, 2.0], // 可选的播放速度
        autoplay: true, // 如果为true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 是否视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [{
          type: "video/mp4", // 类型
          src: '' // url地址
        }],
        poster: '', // 封面地址
        notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true, // 当前时间和持续时间的分隔符
          durationDisplay: true, // 显示持续时间
          remainingTimeDisplay: true, // 是否显示剩余时间功能
          fullscreenToggle: true // 是否显示全屏按钮
        }
      }
    }
  },
  created() {
    this.request("/echarts/file/front/all").then(res => {
      console.log(res.data)
      this.videos = res.data.filter(v => v.type === 'mp4')
    })


  },
  methods: {
    detail(id) {
      this.request("/file/detail/" + id).then(res => {
        console.log(res.data)
        this.playerOptions.sources[0].src = res.data.url
      })
    }
  }
}
</script>

<style>
.item:hover{
  color: #3a8ee6;
  background: none;
}
</style>

