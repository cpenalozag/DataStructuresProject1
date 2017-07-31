package estructuras.test;

import estructuras.IdentificadoUnicamente;
import estructuras.ListaSencillamenteEncadenada;
import estructuras.Pila;
import junit.framework.TestCase;

public class PilaTest extends TestCase
{
protected ListaSencillamenteEncadenada<ElementoBasico> lista = new ListaSencillamenteEncadenada<>();
protected Pila<ElementoBasico> pila = new Pila<>();
    
    
    public void testPush()
    {
        pila.push( new ElementoBasico( "B" ) );
        assertEquals("B", pila.darPrimero().darElemento().darIdentificador());
        pila.push( new ElementoBasico( "C" ) );
        assertEquals("C", pila.darPrimero().darElemento().darIdentificador());
        pila.push( new ElementoBasico( "D" ) );
        assertEquals("D", pila.darPrimero().darElemento().darIdentificador());
    }

    public void testClear( )
    {
        pila.push( new ElementoBasico( "B" ) );
        pila.push( new ElementoBasico( "C" ) );
        pila.push( new ElementoBasico( "D" ) );
        
        pila.clear( );
        
        assertTrue( pila.isEmpty( ) );
        
    }

    public void testContains( )
    {
        ElementoBasico a = new ElementoBasico( "A" );
        assertFalse( pila.contains( a ) );
        pila.add( a );
        assertTrue( pila.contains( a ) );
    }

    public void testGet(  )
    {
        try
        {
            pila.get( -2 );
            fail("Debe lanzar la excepci�n");
        }
        catch( Exception e )
        {
            //Debe lanzar la excepci�n
        }
        try
        {
            lista.get(100);
            fail("Debe lanzar la excepci�n");
        }
        catch( Exception e )
        {
            //Debe lanzar la excepci�n
        }
        
        ElementoBasico b = new ElementoBasico( "B" );
        ElementoBasico c = new ElementoBasico( "C" );
        ElementoBasico d = new ElementoBasico( "D" );
        pila.push( b );
        pila.push( c );
        pila.push( d );
        
        assertEquals( "C",pila.get( 1 ).darIdentificador( ));
    }

    public void testIsEmpty( )
    {
       assertTrue( pila.isEmpty( ) );
       ElementoBasico d = new ElementoBasico( "D" );
       pila.push( d );
       assertFalse( pila.isEmpty( ) );
       
    }


    public void testPop( )
    {
        ElementoBasico a = new ElementoBasico( "A" );   
        ElementoBasico b = new ElementoBasico( "B" );
        ElementoBasico c = new ElementoBasico( "C" );
        ElementoBasico d = new ElementoBasico( "D" );
        pila.push( a );
        pila.push( b );
        pila.push( c );
        pila.push( d );
        
        assertEquals( 4, pila.size( ) );
        
        ElementoBasico r = new ElementoBasico(null);
        assertEquals (d, r = pila.pop());
        assertEquals( 3, pila.size( ) );
        
        ElementoBasico s = new ElementoBasico(null);
        assertEquals (c, s = pila.pop());
        assertEquals( 2, pila.size( ) );
        
        ElementoBasico t = new ElementoBasico(null);
        assertEquals (b, t = pila.pop());
        assertEquals( 1, pila.size( ) );
        
        pila.pop();
        
        assertEquals( 0, lista.size( ) );
        
    }

    public void testSize( )
    {
        ElementoBasico a = new ElementoBasico( "A" );
        ElementoBasico b = new ElementoBasico( "B" );
        ElementoBasico c = new ElementoBasico( "C" );
        ElementoBasico d = new ElementoBasico( "D" );
        
        assertEquals( 0, lista.size( ) );
        
        pila.push( a );
        pila.push( b );
        pila.push( c );
        pila.push( d );
        
        assertEquals( 4, pila.size( ) );
        
        pila.pop();
        
        assertEquals( 3, pila.size( ) );
        
    }
    
    protected class ElementoBasico implements IdentificadoUnicamente
    {
        private static final long serialVersionUID = 1L;
        
        private String valor;
        
        public ElementoBasico( String nValor )
        {
           valor = nValor;
        }
        
        public String darIdentificador( )
        {
            return valor;
        }
        
    }
}
