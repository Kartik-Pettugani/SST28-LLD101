import java.util.*;
public class OnboardingService {

    private final StudentRepository db;
    private final Studentparsing parser;
    private final StudentValidation validator;
    private final StudentPrinter printer;

    public OnboardingService(StudentRepository db,Studentparsing parser,StudentValidation validator,StudentPrinter printer) {
        this.db = db;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);
        StudentInput input = parser.parse(raw);
        List<String> errors = validator.validate(input);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }
        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id,input.name,input.email,input.phone,input.program);
        db.save(rec);
        printer.printSuccess(rec, db.count());
    }
}