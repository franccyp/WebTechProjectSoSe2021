app.component('popUpBox', {
    data() {
        return {
            popupData: {
                "header": "My popup",
                "body": "hello world",
                "footer": "divine inc.",
                "display": "none"
            }
        }
    },
    template: ` 
    <template>
        <div id="popup" :style="{display : popupData.display}">
        <div class="inner">
            <div class="header">
                <div>{{popupData.header}}</div>
                <div @click="closeFunction">Close</div>
        </div>
        <div class="body">{{popupData.body}}</div>
        <div class="footer">{{popupData.footer}}</div>
    </div>
    </div>
    </template>
    <script>
        export default {
        props : ["popupData"],
        methods : {
        closeFunction(){
        this.popupData.display = "none";
    }
    }
    }
    </script>
    
    <style>
        html,body{
        padding: 0;
        margin:0;
    }
        #popup{
        position: absolute;
        width: 100%;
        height :100%;
        top: 0;
    }
        #popup .inner{
        background-color: green;
        position: inherit;
        top: 10%;
        left: 39%;
        width: 300px;
    }
        #popup .inner div{
        text-align: left;
    }
        #popup .inner .header{
        display: flex;
        justify-content: space-between;
    }
    </style>
    `

})