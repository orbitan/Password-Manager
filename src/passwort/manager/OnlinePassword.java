
package passwort.manager;

public class OnlinePassword extends Password{

        private String url;
        
        public OnlinePassword(String service, String username, String password, String url){
            super(service, username, password);
            this.url = url;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }
}
