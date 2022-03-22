package com.devops.dxc.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.devops.dxc.devops.model.Util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevopsApplicationTests {

	//Caso 1
	//Sueldo: $2.000.000
	//Ahorro: $10.000.000
	@Test
	public void testGetDxc_CASO1() throws Exception{
		assertEquals(1000000, Util.getDxc(10000000, 2000000));
	}
	
	@Test
	public void testGetImpuesto_CASO1() throws Exception{
		assertEquals(80000, Util.getImpuesto(10000000, 2000000));
	}
	
	@Test
	public void testGetSaldo_CASO1() throws Exception{
		assertEquals(9000000, Util.getSaldo(10000000, 2000000));
	}

	//Caso 2
	//Sueldo: $1.400.000
	//Ahorro: $30.000.000
	@Test
	public void testGetDxc_CASO2() throws Exception{
		//10%
		assertEquals(3000000, Util.getDxc(30000000, 1400000));
	}
	
	@Test
	public void testGetImpuesto_CASO2() throws Exception{
		//Impuesto
		assertEquals(0, Util.getImpuesto(30000000, 1400000));
	}
	
	@Test
	public void testGetSaldo_CASO2() throws Exception{
		//Saldo
		assertEquals(27000000, Util.getSaldo(30000000, 1400000));
	}

	//Caso 3
	//Sueldo: $2.000.000
	//Ahorro: $18.000.000
	@Test
	public void testGetDxc_CASO3() throws Exception{
		//10%
		assertEquals(1800000, Util.getDxc(18000000, 2000000));
	}
	
	@Test
	public void testGetImpuesto_CASO3() throws Exception{
		//Impuesto
		assertEquals(144000, Util.getImpuesto(18000000, 2000000));
	}
	
	@Test
	public void testGetSaldo_CASO3() throws Exception{
		//Saldo
		assertEquals(16200000, Util.getSaldo(18000000, 2000000));
	}

}
