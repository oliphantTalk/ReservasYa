package layouts.config;

import org.junit.Before;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public abstract class WebSecurityConfigurationAware extends WebAppConfigurationAware {

/*
    @Inject
    private FilterChainProxy springSecurityFilterChain;
*/


    /*@Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
    }*/
}
