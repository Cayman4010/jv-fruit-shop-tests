package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.OperationType;
import core.basesyntax.service.ParseService;
import java.util.ArrayList;
import java.util.List;

public class ParseServiceImpl implements ParseService {
    private static final String WORD_SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int FRUIT_INDEX = 1;
    private static final int AMOUNT_INDEX = 2;

    @Override
    public List<FruitTransaction> parse(List<String> lines) {
        List<FruitTransaction> fruitTransactionList = new ArrayList<>();
        for (String line : lines) {
            String[] lineElements = line.split(WORD_SEPARATOR);
            OperationType operationType = OperationType
                    .getOperationType(lineElements[OPERATION_INDEX]);
            String fruitName = lineElements[FRUIT_INDEX];
            try {
                int amount = Integer.parseInt(lineElements[AMOUNT_INDEX]);
                if (amount < 0) {
                    throw new IllegalArgumentException("Amount can't be less than 0!");
                }
                fruitTransactionList.add(new FruitTransaction(operationType, fruitName, amount));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Parsing the amount failed, "
                        + "because it`s not Integer");
            }
        }
        return fruitTransactionList;
    }
}