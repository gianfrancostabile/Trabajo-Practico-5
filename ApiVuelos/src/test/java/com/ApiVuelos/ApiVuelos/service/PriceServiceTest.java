package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.PriceRepository;
import com.utn.tssi.tp5.Models.model.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringJUnit4ClassRunner.class)
public class PriceServiceTest extends TestCase {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService service;



    Cabin cabin=new Cabin(1,"Economica");
    Price money=new Price(1,1023,"10/12/18","25/01/2019",cabin);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getAllTest() throws Exception{
        List<Price> prices = new ArrayList<Price>();
        prices.add(this.money);
        prices.add(this.money);
        prices.add(this.money);
        when(this.priceRepository.findAll()).thenReturn(prices);
        List<Price>dao=this.service.getAll();
        assertEquals(3, dao.size());
    }
    @Test
    public void getByAttributeTypePricesOffCabinTest() throws Exception{
        when(this.priceRepository.getPriceOfCabinAndDate(this.cabin.getName(), "10/12/18")).thenReturn(java.util.Optional.ofNullable(this.money));
        this.money = this.service.getPriceOfCabinAndDate(this.cabin.getName(), "10/12/18");
        assertNotNull(money);

    }
    @Test
    public void newObjectTest()throws Exception{
        when(this.priceRepository.save(this.money)).thenReturn(this.money);
        Price pc=this.service.newObject(this.money);
        assertEquals(1,pc.getId());
        assertEquals(1023,pc.getPrice(),0);
        assertEquals("10/12/18",pc.getFrom_Date());
        assertEquals("25/01/2019",pc.getTo_Date());

    }
    @Test
    public void removeTest()throws Exception{
        service.removeObject(this.money.getId());
        verify(this.priceRepository,times(1)).deleteById(this.money.getId());
    }
    @Test
    public void getByIdTest() throws Exception{
        when(this.priceRepository.findById(this.money.getId())).thenReturn(java.util.Optional.ofNullable(this.money));
        Price pc = this.service.getById(this.money.getId());
        assertEquals(1,pc.getId());
        assertEquals("10/12/18",pc.getFrom_Date());
        assertEquals("25/01/2019",pc.getTo_Date());

        assertEquals(1023,pc.getPrice(),0);
    }
    @Test
    public void getByAttributeTypeTest() throws Exception{
        Price rte=this.service.getByAttributeType("hola");
        assertNull(rte);
    }
    @Test
    public void getPriceOfCabinAndDate(){
        when(this.priceRepository.getPriceOfCabinAndDate("Economica","10/12/18")).thenReturn(java.util.Optional.ofNullable(this.money));
        Price pc=this.service.getPriceOfCabinAndDate("Economica","10/12/18");
        assertEquals(1,pc.getId());
        assertEquals("10/12/18",pc.getFrom_Date());
        assertEquals("25/01/2019",pc.getTo_Date());

        assertEquals(1023,pc.getPrice(),0);
    }
}
