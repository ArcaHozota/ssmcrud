//package jp.co.toshiba.ppok.test;
//
//import java.util.List;
//
//import com.atdaiwa.entity.City;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.github.pagehelper.PageInfo;
//
///**
// * Use the Spring Test to testing the MVC performance.
// *
// * @author Administrator
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
//public class SpringMVCTest {
//    //Insert the IOC of SpringMVC.
//    @Autowired
//    WebApplicationContext context;
//    //Mock MVC Requests.
//    MockMvc mockMvc;
//
//    @Before
//    public void initMockMvc() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void testPage() throws Exception {
//        //Mock the request and get the return.
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cities").param("pageNum", "7"))
//                .andReturn();
//        //Get the pageInfo from the mocked request.
//        MockHttpServletRequest request = result.getRequest();
//        PageInfo<City> pageInfo = (PageInfo<City>) request.getAttribute("pageInfo");
//        System.out.println("Page:" + pageInfo.getPageNum());
//        System.out.println("Num of all pages:" + pageInfo.getPages());
//        System.out.println("Records:" + pageInfo.getTotal());
//        System.out.println("The continuous number which the screen displayed:");
//        int[] navigatedNums = pageInfo.getNavigatepageNums();
//        for (int i : navigatedNums) {
//            System.out.print(" " + i);
//        }
//        System.out.println(" ");
//        //Get the city data.
//        List<City> citiesList = pageInfo.getList();
//        for (City city : citiesList) {
//            System.out.println("ID:" + city.getId() + "===>Name:" + city.getName());
//        }
//    }
//}
