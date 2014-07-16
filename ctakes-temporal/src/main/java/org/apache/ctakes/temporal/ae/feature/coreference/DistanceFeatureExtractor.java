package org.apache.ctakes.temporal.ae.feature.coreference;

import java.util.ArrayList;
import java.util.List;

import org.apache.ctakes.relationextractor.ae.features.RelationFeaturesExtractor;
import org.apache.ctakes.temporal.utils.CorefConst;
import org.apache.ctakes.typesystem.type.syntax.BaseToken;
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation;
import org.apache.ctakes.typesystem.type.textspan.Sentence;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.cleartk.classifier.Feature;
import org.uimafit.util.JCasUtil;

public class DistanceFeatureExtractor implements RelationFeaturesExtractor {

	@Override
	public List<Feature> extract(JCas jCas, IdentifiedAnnotation arg1,
			IdentifiedAnnotation arg2) throws AnalysisEngineProcessException {
		List<Feature> feats = new ArrayList<>();
		feats.add(new Feature("TOK_DIST",
				  JCasUtil.selectCovered(jCas, BaseToken.class, arg1.getBegin(), arg2.getEnd()).size() / (double)CorefConst.TOKEN_DIST));
		feats.add(new Feature("SENT_DIST",
				JCasUtil.selectCovered(jCas, Sentence.class, arg1.getBegin(), arg2.getEnd()).size() / (double) CorefConst.NE_DIST));
		return feats;
	}

}
