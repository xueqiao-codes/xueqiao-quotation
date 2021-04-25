cc_library(
    name = 'quotation_access_service',
    srcs = [
        'src/gen/quotation_access_service_constants.cpp',
        'src/gen/quotation_access_service_types.cpp',
        'src/gen/QuotationAccessService.cpp',
        'src/gen/QuotationAccessService_variables.cpp',
        'src/gen/client/quotation_access_service_stub.cpp',
    ],
    export_incs = [
        'src/gen',
        'src/gen/client',
    ],
    deps = [
        '//quotation_common/quotation/account/dao:quotation_account_dao',
    ]
)