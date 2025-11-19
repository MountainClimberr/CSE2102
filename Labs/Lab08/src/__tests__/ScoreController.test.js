import ScoreController from '../controller/ScoreController';

describe('ScoreController Tests', () => {
    
    test('starts at 0', () => {
        const controller = new ScoreController();
        expect(controller.getScore()).toBe(0);
        expect(controller.getCount()).toBe(0);
    });

    test('incrementScore increases both', () => {
        const controller = new ScoreController();
        controller.incrementScore();
        expect(controller.getScore()).toBe(1);
        expect(controller.getCount()).toBe(1);
    });

    test('dontIncrementScore only increases count', () => {
        const controller = new ScoreController();
        controller.dontIncrementScore();
        expect(controller.getScore()).toBe(0);
        expect(controller.getCount()).toBe(1);
    });

    test('reset works', () => {
        const controller = new ScoreController();
        controller.incrementScore();
        controller.reset();
        expect(controller.getScore()).toBe(0);
        expect(controller.getCount()).toBe(0);
    });
});
