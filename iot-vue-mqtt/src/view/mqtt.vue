<!--webSocket测试页面-->
<template>
  <div>
    <div>收到mqtt数据:{{ msg }}</div>
    <div>
      订阅主题：
      <input type="text" v-model="mqttTopic">
      <el-button @click="sub">订阅</el-button>
    </div>
    <div>
      <input type="text" v-model="mqttText">
      <el-button @click="pub">发送</el-button>
    </div>

  </div>
</template>

<script>
  import mqtt from 'mqtt'
  import {MQTT_SERVICE, MQTT_USERNAME, MQTT_PASSWORD, MQTT_TOPIC} from '@/../config/mqtt.env'

  var client
  const options = {
    connectTimeout: 40000,
    clientId: 'mqtitId-Home',
    username: MQTT_USERNAME,
    password: MQTT_PASSWORD,
    topic: MQTT_TOPIC,
    clean: true
  }
  client = mqtt.connect(MQTT_SERVICE, options)
  export default {
    name: 'mqtt',
    data() {
      return {
        msg: '',
        mqttText: '',
        mqttTopic: ''
      }
    },
    created() {
      this.mqttMSG();
    },
    methods: {
      mqttMSG() {
        // mqtt连接
        client.on('connect', (e) => {
          console.log('连接成功:')
        }),
          // 接收消息处理
          client.on('message', (topic, message) => {
            console.log('收到来自', topic, '的消息', message.toString())
            this.msg = message.toString()
          }),
          // 断开发起重连
          client.on('reconnect', (error) => {
            console.log('正在重连:', error)
          }),
          // 链接异常处理
          client.on('error', (error) => {
            console.log('连接失败:', error)
          })
      },
      sub: function () {
        client.subscribe(this.topic, {qos: 0}, (error) => {
          if (!error) {
            console.log('订阅成功')
          } else {
            console.log('订阅失败')
          }
        })
      },
      pub: function () {
        client.publish('2222', this.mqttText);
      }
    }
  }
</script>
