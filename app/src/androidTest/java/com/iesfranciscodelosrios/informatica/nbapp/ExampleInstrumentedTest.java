package com.iesfranciscodelosrios.informatica.nbapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4 ;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.iesfranciscodelosrios.informatica.nbapp", appContext.getPackageName());
    }
    /*
        @Before
        public void setUp(){
            InstrumentationRegistry.getTargetContext().deleteDatabase(Constantes.getnbaDB());
            repositorio = Repositorio.getInstance(InstrumentationRegistry.getTargetContext());
        }

        @After
        public void tearDown() {
            repositorio.close();
        }

        @Test
        public void crudDB() {
            Pregunta p_nueva = new Pregunta(
                    "Enunciado",
                    "Categoría",
                    "Respuesta",
                    "Respuesta",
                    "Respuesta",
                    "Respuesta",
                    ""
            );
            assertEquals(true, repositorio.insertar(p_nueva));

            ArrayList<Pregunta> preguntas = repositorio.recuperarPreguntas();
            assertEquals(1, preguntas.size());

            assertEquals("Enunciado", preguntas.get(0).getEnunciado());

            Pregunta p2 = new Pregunta(
                    "1",
                    "Enunciado2",
                    "Categoría2",
                    "Respuesta2",
                    "Respuesta2",
                    "Respuesta2",
                    "Respuesta2",
                    ""
            );
            assertEquals(true, repositorio.actualizarPregunta(p2));

            preguntas = repositorio.recuperarPreguntas();
            assertEquals(1, preguntas.size());

            assertEquals("Enunciado2", preguntas.get(0).getEnunciado());

            assertEquals(false, repositorio.borrarPregunta(100));

            assertEquals(true, repositorio.borrarPregunta(preguntas.get(0).getCodigo()));

            preguntas = repositorio.recuperarPreguntas();
            assertEquals(0, preguntas.size());

    }

     */
}
