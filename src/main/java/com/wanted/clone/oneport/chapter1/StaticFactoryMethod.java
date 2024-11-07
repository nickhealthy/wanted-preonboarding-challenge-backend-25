package com.wanted.clone.oneport.chapter1;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 정적 팩토리 메서드 예제
 */
@Slf4j
public class StaticFactoryMethod {

    public static void main(String[] args) {
        Lotto autoLotto = LottoFactory.createAutoLotto();
        System.out.println("autoLotto = " + autoLotto);
    }


    public static class LottoFactory {
        private static final int LOTTO_SIZE = 6;
        private static final int MIN_LOTTO_NUMBER = 1;
        private static final int MAX_LOTTO_NUMBER = 45;

        private static List<Integer> allLottoNumbers;

        static {
            IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER).forEach(i -> allLottoNumbers.add(i));
        }

        public static Lotto createAutoLotto() {
            Collections.shuffle(allLottoNumbers);
            return new Lotto(allLottoNumbers.stream()
                    .limit(LOTTO_SIZE)
                    .collect(Collectors.toList()));
        }

        public static Lotto createManualLotto(List<Integer> lottoNumbers) {
            return new Lotto(lottoNumbers);
        }

    }

    private static class Lotto<T> {

        private List<T> list;

        public Lotto(List<T> list) {
            this.list = list;
        }
    }
}
