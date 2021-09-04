
package passwordManager;

public class OnlinePassword extends Password{

        private final String url;
        
        public OnlinePassword(String service, String username, String password, String url){
            super(service, username, password);
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }
        @Override
        public String toString(){
            return super.toString()
                    + " " + this.url;
        }
}
