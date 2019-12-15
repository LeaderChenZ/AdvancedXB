var vm = new Vue({
    el: '#page-content',
    data: {
        dept: {},
        userName: {},
        meeting: {
            title: '',
            content: '',
            startTime: '',
            endTime: '',
        }
    },
    methods: {
        selectAll: function () {
            axios({
                url: "/xbjy/dept/index"
            }).then(res => {
                this.dept = res.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        selectUser: function () {
            let did = $("#deptId").val();
            axios({
                url: "/xbjy/dept/selectUser",
                params: {
                    did: did
                }
            }).then(res => {
                this.userName = res.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        insertMeeting: function () {
            this.meeting.deptId = $("#deptId").val();
            this.meeting.makeUser = $("#userId").val().toString();
            this.meeting.startTime = $("#startTime").val();
            this.meeting.endTime = $("#endTime").val();
            if (title === "" || this.meeting.startTime === "" || this.meeting.endTime === "" ||
                this.meeting.deptId === "" || this.meeting.makeUser === "") {
                layer.msg("请输入完整！");
                return;
            }
            if(this.meeting.startTime>=this.meeting.endTime){
                layer.msg("请输入有效的起止时间！");
                return;
            }
            console.log(this.meeting);
            axios({
                url: "/xbjy/meeting/insertMeeting",
                data: this.meeting,
                method: "post"
            }).then(res => {
                layer.msg(res.data.msg);
                if (res.data.success) {
                    layer.msg(res.data.msg);
                    location.href = "/html/meeting.html"
                }
            }).catch(function (error) {
                console.log(error);
            });
        }
    },
    created: function () {
        $("#deptId").append('<option value="" selected>请选择部门</option>');
        this.selectAll();
    },
    updated: function () {
        $("#deptId").selectpicker('refresh');
        $("#userId").selectpicker('refresh');
    }
})