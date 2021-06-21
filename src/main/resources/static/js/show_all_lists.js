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
                                <li class="slist_items">{{list_item.item_name}}  <button class="slist_small_button" @click="delete_item(list_item)"> ✖ </button> <button class="slist_small_button" @click="check_item(list_item)">  ✔ </button> </li>
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
        delete_item(item) {
            let delete_url = '/item/remove/' + item.item_id
            axios.post(delete_url)
                .then((response) => {
                    this.load_lists();
                }, (error) => {
                    Swal.fire('could not delete item!');
                    console.log('could not delete item!');
                });
        },
        check_item(item) {
            let checked_itemName = item.item_name
                .split('')
                .map(char => char + '\u0336')
                .join('')
            console.log(checked_itemName)
            let url = '/item/updatename/' + item.item_id + '/' + checked_itemName
            axios.put(url, {
                itemName: checked_itemName
            }).then((updatedItem) => {
                this.load_lists();
            }, (error) => {
                Swal.fire('could not check item!');
                console.log('could not check item!');
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
