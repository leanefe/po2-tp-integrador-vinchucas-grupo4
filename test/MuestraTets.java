import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


class MuestraTets {
	
	Muestra muestra;
	Participante p1;
	Opinion o1;
	Opinion o2;
	Opinion o3;
	Ubicacion u1;
	ZonaCobertura z1;
	
	@BeforeEach
	void setUp() {
		p1 = mock(Participante.class);
		o1 = mock(Opinion.class);
		o2 = mock(Opinion.class);
		o3 = mock(Opinion.class);
		u1 = mock(Ubicacion.class);
		z1 = mock(ZonaCobertura.class);
		muestra = new Muestra("fotoURL", EspecieVinchuca.INFESTANS, p1,u1);
	}

	@Test
	void SinOpinionesElTipoEsNinguna() {
		assertEquals(TipoOpinion.NINGUNA, muestra.resultadoActualOpiniones());
	}
	
	@Test
	void AgregoOpinionYElTipoEsDeLaOpinionQueAgregue() {
		when(o1.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		muestra.addOpinion(o1);
		assertEquals(TipoOpinion.PHTIA_CHINCHE, muestra.resultadoActualOpiniones());
	}
	
	@Test
	void Agrego2OpinionYElTipoEsDeLaOpinionQueAgregue() {
		when(o1.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o2.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
		when(o2.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		muestra.addOpinion(o1);
		muestra.addOpinion(o2);
		assertEquals(TipoOpinion.PHTIA_CHINCHE, muestra.resultadoActualOpiniones());
	}
	
	@Test
	void Agrego2OpinionYElTipoDeLaOpinionEsNingunaPqNoCoinciden() {
		when(o1.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o2.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
		when(o2.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		muestra.addOpinion(o1);
		muestra.getUltimaFechaVotacion();
		muestra.addOpinion(o2);
		assertEquals(TipoOpinion.NINGUNA, muestra.resultadoActualOpiniones());
	}
	
	@Test
	void AgregoTresOpinionesYElTipoDeLaOpinionEsNingunaPqNoCoinciden() { 
		when(o1.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o2.getTipo()).thenReturn(TipoOpinion.IMG_POCO_CLARA);
		when(o2.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o3.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
		when(o3.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		muestra.addOpinion(o1);
		muestra.addOpinion(o2);
		muestra.addOpinion(o3);
		assertEquals(TipoOpinion.NINGUNA, muestra.resultadoActualOpiniones());
	}
	
	@Test
	void UnexpertoOpindadaYSeBorraHistorial() {
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o1.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
		when(o2.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o2.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(o3.getNivelConocimiento()).thenReturn(NivelParticipante.EXPERTO);
		when(o3.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		muestra.addOpinion(o1);
		muestra.addOpinion(o2);
		assertEquals(2, muestra.getOpiniones().size());
		muestra.addOpinion(o3);
		assertEquals(1, muestra.getOpiniones().size());
	}
	
	@Test
	void UnexpertoOpindadaYcambiaDeEstadoYYaNoPuedeVotarNingunBasico() {
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.EXPERTO);
		when(o1.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
		when(o2.getNivelConocimiento()).thenReturn(NivelParticipante.EXPERTO);
		when(o2.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(o3.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o3.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		muestra.addOpinion(o1);
		muestra.addOpinion(o2);
		muestra.addOpinion(o3);
		assertEquals(2, muestra.getOpiniones().size());
	}
	
	@Test
	void DosxpertosOpinanYCoincidenYcambiaDeEstado() {
		assertEquals(NivelVerificacion.VOTADA, muestra.getNivelVerificacion());
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.EXPERTO);
		when(o1.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(o2.getNivelConocimiento()).thenReturn(NivelParticipante.EXPERTO);
		when(o2.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		muestra.addOpinion(o1); // Pasa a Muestra de Expertos
		assertEquals(NivelVerificacion.VOTADA, muestra.getNivelVerificacion());
		muestra.addOpinion(o2); // Pasa a Muestra Verificada
		assertEquals(NivelVerificacion.VERIFICADA, muestra.getNivelVerificacion());
	}
	
	@Test 
	void MuestraEnEstadoVerificadoNoSePuedeVotar() {
		muestra.setEstado(new MuestraVerificada());
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o1.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
		when(o2.getNivelConocimiento()).thenReturn(NivelParticipante.EXPERTO);
		when(o2.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		muestra.addOpinion(o1);
		muestra.addOpinion(o2);
		assertEquals(0, muestra.getOpiniones().size());
	}
	
	@Test
	void MuestraCambiaDeEstadoYAvisaALasZonaDeCobertura() {
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.EXPERTO);
		when(o1.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(o2.getNivelConocimiento()).thenReturn(NivelParticipante.EXPERTO);
		when(o2.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		muestra.agregarZona(z1);
		muestra.addOpinion(o1);
		muestra.addOpinion(o2);
		verify(z1, atLeastOnce()).avisarValidacionMuestra(muestra);;
	}


	@Test
	void dosOpinionesFrenteAUnaTest() {
		when(o1.getTipo()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(o1.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o2.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
		when(o2.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		when(o3.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
		when(o3.getNivelConocimiento()).thenReturn(NivelParticipante.BASICO);
		muestra.addOpinion(o1);
		muestra.addOpinion(o2);
		muestra.addOpinion(o3);
		assertEquals(TipoOpinion.VINCHUCA, muestra.resultadoActualOpiniones());
	}

	@Test
	void testGetters() {
		muestra.getFechaCreacion();
		assertEquals(p1, muestra.getRecolector());
		assertEquals(u1, muestra.getUbicacion());
	}
}
