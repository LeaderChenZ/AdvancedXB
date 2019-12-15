
var vm = new Vue({
    el: '.myBox',
    data: function() {
        return{
        params:{
            email: '',
            password: '',
        },show: true,
            timer: null,
            count: ''
        }
    },
    methods: {
        send: function () {
            axios({
                url: 'manager/login/send',
                method: "post",
                data: this.params
            }).then(response => {
                layer.msg(response.data.msg)
            }).catch(function (error) {
                console.log(error);
            })
        },
        register: function () {
            axios({
                url: '/xbjy/login/forget',
                method: "post",
                data: this.params
            }).then(response => {
                // console.log(response.data)
                let data = response.data;
                if (data.success == false) {//邮箱有误
                    layer.msg(data.msg, {time: 1000}, function () {
                        location.href = 'forget.html';
                    });
                    layer.msg(response.data.msg)
                } else {//修改成功
                    layer.msg(data.msg, {time: 1000}, function () {
                        location.href = 'index.html';
                    });
                }
                console.log(response.data)
            }).catch(function (error) {
                console.log(error);
            })
        },getCode() {
            axios({
                url: '/xbjy/login/send',
                method: "post",
                data: this.params
            }).then(response => {
                // console.log(response.data);
                this.show = false;
                const TIME_COUNT = 60;
                if (!this.timer) {
                    this.count = TIME_COUNT;
                    this.show = false;
                    this.timer = setInterval(() => {
                        if (this.count > 0 && this.count <= TIME_COUNT) {
                            this.count--;
                        } else {
                            this.show = true;
                            clearInterval(this.timer);
                            this.timer = null;
                        }
                    }, 1000)
                }
            }).catch(function (error) {
                console.log(error);
            })
        }
    },
    created: function () {

    }

});