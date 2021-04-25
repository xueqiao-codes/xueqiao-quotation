cc_library(
    name = 'access_common',
    srcs = [
        'accessservice_impl.cpp',
        'accessstate_holder.cpp',
        'contract_mapping.cpp',
        'contract_symbol_utils.cpp',
        'daily_restart_worker.cpp',
        'effective_reporter.cpp',
        'group_chooser.cpp',
        'price_utils.cpp',
        'scitem_manager.cpp',
    ],
    deps = [
        '//quotation_common/quotation/contract:contract_online',
        '//quotation_common/quotation/race/client:quotation_race_client',
        '//quotation_common/quotation/plan/bo:quotation_plan_bo',
        '//quotation_common/quotation/account/dao:quotation_account_dao',
        '//quotation_common/quotation/access/service:quotation_access_service',
        '//quotation_common/thrift:quotation_thrift',
        '//cpp_platform/attr:soldier_attr',
        '//cpp_platform/thirdparty/rapidjson:rapidjson',
        '//cpp_platform/watcher:file_watcher_module',
        '//contract_convertor:contract_convertor',
    ],
    export_incs = [
        '.',
        '../../..',
    ]
)