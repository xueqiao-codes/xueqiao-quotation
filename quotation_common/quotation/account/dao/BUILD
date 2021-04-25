cc_library(
    name = 'quotation_account_dao',
    srcs = [
        'src/gen/quotation_account_constants.cpp',
        'src/gen/quotation_account_types.cpp',
        'src/gen/quotation_account_dao_constants.cpp',
        'src/gen/quotation_account_dao_types.cpp',
        'src/gen/QuotationAccountDao.cpp',
        'src/gen/QuotationAccountDao_variables.cpp',
        'src/gen/client/quotation_account_dao_stub.cpp',
    ],
    export_incs = [
        'src/gen',
        'src/gen/client',
    ],
    deps = [
        '//quotation_common/quotation/page:thrift_page',
    ]
)