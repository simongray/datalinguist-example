(ns datalinguist-example
  (:require [clojure.datafy :refer [datafy]]
            [dk.simongray.datalinguist :as dl]
            [dk.simongray.datalinguist.triple :refer [triple->datalog]])
  (:import [edu.stanford.nlp.coref CorefCoreAnnotations$CorefChainAnnotation]))

(def example
  "Great work, thanks for doing this!
  Having had no previous experience with NLP libraries, I was wondering why I couldn’t get your examples to work. Then I realized that I had to download CoreNLP first from https://stanfordnlp.github.io/CoreNLP/ and add stanford-corenlp-4.4.0/* to the classpath. Everything worked fine after that.
  Is this what you are supposed to do? It wasn’t mentioned in the readme, so I was wondering if I did something wrong here or if it is more obvious to people who have already worked with CoreNLP.")

(def nlp
  (dl/->pipeline {:annotators ["truecase"
                               "quote"
                               "entitymentions"
                               "parse"
                               "depparse"
                               "lemma"
                               "coref"
                               "openie"
                               "ner"]
                  :quote      {:extractUnclosedQuotes "true"}}))

(def annotated-example
  (delay (nlp example)))

(def sentences
  (delay (dl/sentences @annotated-example)))

(comment
  ;; Test every annotator in the pipeline
  (map dl/true-case @sentences)
  (map dl/quotations @sentences)
  (map dl/mentions @sentences)
  (map dl/constituency-tree @sentences)
  (map dl/dependency-graph @sentences)
  (map dl/lemma @sentences)
  (map dl/mentions @sentences)
  (->> (mapcat dl/triples @sentences) (map triple->datalog))
  (dl/annotation CorefCoreAnnotations$CorefChainAnnotation @annotated-example)

  ;; Datafy the annotations. Retrieves direct annotations for every sentence.
  ;; Keep in mind that `dl/recur-datafy` currently doesn't work in this instance
  ;; and will possibly be removed in a future update:
  ;;   https://github.com/simongray/datalinguist/issues/13
  (map datafy @sentences)
  #_.)
