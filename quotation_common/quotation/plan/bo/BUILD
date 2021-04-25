cc_library(
    name = 'quotation_plan_bo',
    srcs = [
        'src/gen/quotation_plan_bo_constants.cpp',
        'src/gen/quotation_plan_bo_types.cpp',
        'src/gen/QuotationPlanBo_variables.cpp',
        'src/gen/QuotationPlanBo.cpp',
        'src/gen/client/quotation_plan_bo_stub.cpp',
    ],
    export_incs = [
        'src/gen',
        'src/gen/client',
    ],
    deps = [
        '//quotation_common/quotation/page:thrift_page',
        '//quotation_common/quotation/account/dao:quotation_account_dao',
    ]
)