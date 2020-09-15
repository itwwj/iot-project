<template>
  <div>
    <div>测试数据:{{ msg }}</div>
  </div>
</template>

<script>
  import mqtt from 'mqtt'
  import { MQTT_SERVICE, MQTT_USERNAME, MQTT_PASSWORD } from '../../config/sysconstant.js'
  var client
  const options = {
    connectTimeout: 40000,
    clientId: 'mqtitId-Home',
    username: MQTT_USERNAME,
    password: MQTT_PASSWORD,
    clean: true
  }
  client = mqtt.connect(MQTT_SERVICE, options)
  export default {
    name: 'VueMqtt',
    data () {
      return {
        msg: ''
      }
    },
    created () {
      this.mqttMSG()
    },
    methods: {
      mqttMSG () {
        // mqtt连接
        client.on('connect', (e) => {
          console.log('连接成功:')
          client.subscribe('/World1234', { qos: 1 }, (error) => {
            if (!error) {
              console.log('订阅成功')
            } else {
              console.log('订阅失败')
            }
          })
        })
        // 接收消息处理
        client.on('message', (topic, message) => {
          console.log('收到来自', topic, '的消息', message.toString())
          this.msg = message.toString()
        })
        // 断开发起重连
        client.on('reconnect', (error) => {
          console.log('正在重连:', error)
        })
        // 链接异常处理
        client.on('error', (error) => {
          console.log('连接失败:', error)
        })
      }
    }
  }
</script>
