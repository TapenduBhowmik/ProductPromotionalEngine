package com.example.demo.rules;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public  class RuleEngine {
	
	public static Rules getRuleInstance() {
		Rules rule = new Rules();
		rule.register(new ProductAPromotionRules());
		rule.register(new ProductBPromotionRules());
		rule.register(new ProductCPromotionRules());
		return rule;
	}
	
	public static RulesEngine getRulesEngineInstance() {
		RulesEngineParameters parameter = new RulesEngineParameters().skipOnFirstAppliedRule(true);
		RulesEngine produtRulesEngine = new DefaultRulesEngine(parameter);
		return produtRulesEngine;
	}

}
