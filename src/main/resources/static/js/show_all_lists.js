export default {

    data() {
        return {
            shopping_lists: [],
            search: ''
        };
    },
    template: `
<h1 class="shopping_list_title">Here are all the shopping lists created</h1>     
<input class="inputbox searchbar" type="text" v-model="search" placeholder="search list by Title"/>
<div class="shopping_list_body">
            <ul class="shopping_list" v-for="shopping_list in filtered_lists">
                    <li> <text class="slist_small_title">{{shopping_list.list_name}}</text> <button class="slist_small_button" @click="delete_list(shopping_list.list_id)"> ✖ </button> </li>  
                    <li>          
                        <div>
                            <ul v-for="list_item in shopping_list.listItems">
                                <li class="slist_items">{{list_item.item_name}}</li>
                            </ul>
                        </div>
                    </li>
            </ul>
            <ul class="shopping_list">
            <li><button class="slist_small_button"  type="button" @click="redirect_to_create_list">Create a new List!</button></li> 
                <ul>
                   <li class="slist_items">...</li>   
                   <li class="slist_items">...</li>
                   <li class="slist_items">...</li>      
                </ul>
            </ul>
</div>
    `,
    computed: {
        filtered_lists() {
            return this.shopping_lists.filter(shopping_list => {
                return shopping_list.list_name.toLowerCase().indexOf(this.search.toLowerCase()) > -1
            })
        }
    },
    methods: {
        redirect_to_create_list() {
            window.location.href = "/createlist"
        },
        delete_list(list_id) {
            let delete_url = '/shoppinglists/remove/' + list_id
            axios.post(delete_url)
                .then((response) => {
                    this.load_lists();
                    Swal.fire('List deleted!');
                }, (error) => {
                    Swal.fire('could not delete list!');
                    console.log('could not delete list!');
                });
        },
        load_lists() {
            fetch('/shoppinglists').then((response) => {
                if (response.ok) {
                    return response.json();
                }
            }).then((data) => {
                this.shopping_lists = data;
            }, (error) => {
                console.log('could not load lists');
            });
        }
    },
    mounted: function () {
        this.load_lists();
    }
}
