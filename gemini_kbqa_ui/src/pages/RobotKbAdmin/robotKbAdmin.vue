<template>
  <div class="app-app-container">
        <div class="filter-container">
            <el-table
                :key="tableKey"
                 v-loading="listLoading"
                :data="list"
                border
                fit
                highlight-current-row
                 style="width: 100%;"
                 @sort-change="sortChange"
            >
            <el-table-column label="id" width="150px" align="center">
                <template>
                    <span>1</span>
                </template>
            </el-table-column>

            </el-table>
        </div>
    </div>

</template>
<script>
export default {
    
}
</script>