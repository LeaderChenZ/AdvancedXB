var vm = new Vue({
    el: '#main-container',
    data: {
        obj:{

        },
        params:{
            arrivals:'',
            late:''
        },
        flag:false

    },
    methods: {
        selectAll: function () {
           let mid = this.obj.id;
            axios({
                url: "/xbjy/meeting/selectDetail",
                params:{
                    mid:mid
                }
            }).then(res => {
                this.params = res.data;
            }).catch(function (error) {
                console.log(error)
            });
        },
        addMeeting:function () {
            let mid = this.obj.id;
            axios({
                url: "/xbjy/meeting/addMeeting",
                params:{
                    mid:mid
                }
            }).then(res => {
                if (res.data.success){
                    this.flag=true;
                    layer.msg(res.data.msg);
                    this.selectAll();
                }
            }).catch(function (error) {
                console.log(error)
            });
        },
        deleteMeeting:function () {
            let mid = this.obj.id;
            axios({
                url: "/xbjy/meeting/deleteMeeting",
                params:{
                    mid:mid
                }
            }).then(res => {
                if (res.data.success){
                    this.flag=false;
                    this.selectAll();
                    layer.msg(res.data.msg, {time: 1000}, function () {
                        let index= parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
            }).catch(function (error) {
                console.log(error)
            });
        }


    },
    created: function () {
        this.obj = parent.layer.obj;;
        this.selectAll();
    }

});